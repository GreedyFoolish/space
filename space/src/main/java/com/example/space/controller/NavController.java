package com.example.space.controller;

import com.example.space.dto.SpaceNavDTO;
import com.example.space.enums.ResponseCodeEnum;
import com.example.space.exception.BusinessException;
import com.example.space.model.ResponseEntity;
import com.example.space.service.SpaceNavService;
import com.example.space.util.JwtUtil;
import com.example.space.vo.SpaceNavVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
@Tag(name = "导航管理", description = "导航相关操作接口")
public class NavController {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(NavController.class);
    private final SpaceNavService spaceNavService;
    private final JwtUtil jwtUtil;

    public NavController(SpaceNavService spaceNavService, JwtUtil jwtUtil) {
        this.spaceNavService = spaceNavService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/list")
    @Operation(summary = "根据名称和状态查询导航", description = "根据提供的导航名称和状态筛选导航列表")
    @ApiResponse(responseCode = "200", description = "成功返回筛选后的导航列表")
    public ResponseEntity<List<SpaceNavDTO>> getNavList(
        @Parameter(description = "用户token") @RequestHeader("Authorization") String requestToken,
        @Parameter(description = "导航名称") @RequestParam(value = "navName", required = false) String navName,
        @Parameter(description = "导航状态") @RequestParam(value = "status", required = false) List<Boolean> status
    ) {
        if (requestToken == null || !requestToken.startsWith("Bearer ")) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1002.getCode(), "无效的token");
        }
        // 记录搜索导航列表的参数
        logger.info("搜索导航列表，名称：{}，状态：{}", navName, status);
        // 从请求头中获取token
        String token = requestToken.substring(7);
        // 验证token
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1003.getCode(), "无效的token或token已过期");
        }
        // 从请求头中获取用户ID
        Long userId = jwtUtil.getUserIdFromToken(token);
        // 使用 userId 查询导航菜单
        List<SpaceNavDTO> navList = spaceNavService.getNavsByUserId(userId, navName, status);
        // 记录搜索导航列表成功的结果
        logger.info("搜索导航列表成功，结果：{}", navList);
        // 返回导航列表
        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), navList);
    }

    @PostMapping
    @Operation(summary = "添加导航菜单", description = "根据提供的导航信息添加新的导航菜单")
    @ApiResponse(responseCode = "200", description = "成功添加导航菜单")
    public ResponseEntity<String> addNavMenu(
        @Parameter(description = "用户token") @RequestHeader("Authorization") String requestToken,
        @RequestBody @Valid SpaceNavVO spaceNavVO
    ) {
        if (requestToken == null || !requestToken.startsWith("Bearer ")) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1002.getCode(), "无效的token");
        }

        // 提取 token 和验证逻辑
        String token = requestToken.substring(7);
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1003.getCode(), "无效的token或token已过期");
        }

        // 获取用户ID
        Long userId = jwtUtil.getUserIdFromToken(token);
        // 调用 service 层添加导航菜单
        String result = spaceNavService.addNavMenu(spaceNavVO, userId);

        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), result, null);
    }

}
