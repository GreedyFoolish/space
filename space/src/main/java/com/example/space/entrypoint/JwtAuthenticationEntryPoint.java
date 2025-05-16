package com.example.space.entrypoint;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.model.ResponseEntity;
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
        response.setContentType("application/json;charset=UTF-8");
        String requestURI = request.getRequestURI();
        logger.warn("请求被拒绝访问: {} {}", requestURI, ex.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseEntity<String> result = ResponseEntity.custom(
            ResponseCodeEnum.UNAUTHORIZED.getCode(),
            "无效的token或token已过期",
            null
        );
        response.getWriter().write(ResponseEntity.serialize(result));
    }

}
