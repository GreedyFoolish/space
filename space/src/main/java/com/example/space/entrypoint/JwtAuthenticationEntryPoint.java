package com.example.space.entrypoint;

import com.example.space.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        String message = "无效的token或token已过期";
        logger.warn("请求被拒绝访问: {} {}", request.getRequestURI(), message);
        ResponseUtil.writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, message);
    }

}
