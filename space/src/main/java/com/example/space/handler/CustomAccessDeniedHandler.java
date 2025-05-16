package com.example.space.handler;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.model.ResponseEntity;
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
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String message = "权限不足，请求被拒绝访问";
        String requestURI = request.getRequestURI();
        logger.warn("请求被拒绝访问: {} {}", requestURI, message);
        ResponseEntity<String> result = ResponseEntity.custom(
            ResponseCodeEnum.FORBIDDEN.getCode(),
            message,
            null
        );
        response.getWriter().write(ResponseEntity.serialize(result));
    }

}
