package com.example.space.controller;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.exception.BusinessException;
import com.example.space.model.ResponseEntity;
import com.example.space.model.User;
import com.example.space.service.CaptchaService;
import com.example.space.util.JwtUtil;
import com.example.space.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证管理", description = "用户登录及 JWT 生成")
@AllArgsConstructor
public class AuthController {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private CaptchaService captchaService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "通过用户名和密码获取 JWT")
    @ApiResponse(responseCode = "200", description = "成功返回 JWT")
    public ResponseEntity<Map<String, String>> login(
        @Valid @Parameter(description = "用户信息") @RequestBody User user,
        @Parameter(description = "验证码的key") @RequestHeader("X-Captcha-Key") String captchaKey,
        @Parameter(description = "验证码的value") @RequestHeader("X-Captcha-Code") String captcha
    ) {
        // 验证码校验
        if (captchaKey == null || captcha == null || !captchaService.validateCaptcha(captchaKey, captcha)) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1001.getCode(), "验证码错误");
        }
        // 进行身份验证
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
        );
        // 如果认证成功，设置 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户角色
        String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        // 生成 JWT 令牌
        String token = jwtUtil.generateToken(user.getName(), role);
        // 返回包含令牌的响应
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.success(response);
    }


    @GetMapping("/captcha")
    @Operation(summary = "获取验证码", description = "返回验证码图片")
    @ApiResponse(responseCode = "200", description = "返回验证码图片")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        try {
            // 生成验证码的 key
            String captchaKey = UUID.randomUUID().toString();
            // 生成验证码
            String captchaValue = captchaService.generateCaptchaCode();
            // 保存验证码到 Redis
            captchaService.saveCaptcha(captchaKey, captchaValue);
            // 设置响应头，使浏览器识别为图片
            response.setContentType("image/png");
            // 将 captchaKey 添加到响应头中
            response.setHeader("X-Captcha-Key", captchaKey);
            // 生成验证码图片
            BufferedImage image = captchaService.generateCaptchaImage(captchaValue);
            ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (IOException e) {
            logger.error("无法生成验证码", e);
            ResponseUtil.writeErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "无法生成验证码");
        }
    }

}
