package com.example.space.interceptor;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.exception.BusinessException;
import com.example.space.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    // JWT工具类
    private final JwtUtil jwtUtil;

    public AuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 Token 信息
        String requestToken = request.getHeader("Authorization");
        // 校验 Token 是否存在且格式正确
        if (requestToken == null || !requestToken.startsWith("Bearer ")) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1002.getCode(), "无效的token");
        }
        // 提取 Token
        String token = requestToken.substring(7);
        // 验证 Token 是否有效
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(ResponseCodeEnum.CUSTOM_ERROR_1003.getCode(), "无效的token或token已过期");
        }
        // 将 userId 存入 request 属性中，供 Controller 使用
        Long userId = jwtUtil.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        // 放行
        return true;
    }

}
