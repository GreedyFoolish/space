package com.example.space.handler;

import com.example.space.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException {
        String message = "权限不足，请求被拒绝访问";
        logger.warn("请求被拒绝访问: {} {}", request.getRequestURI(), message);
        ResponseUtil.writeErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, message);
    }

}
