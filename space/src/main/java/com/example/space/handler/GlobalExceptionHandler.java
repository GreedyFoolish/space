package com.example.space.handler;

import com.example.space.model.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String[] EXCLUDE_PATHS = {
            "/v3/api-docs",
            "/swagger-ui",
            "/actuator"
    };

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Global exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.failure(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.failure(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        logger.error("NoSuchElementException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.failure(ex.getMessage());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果返回类型已经是 ResponseEntity，则不再包装
        if (returnType.getParameterType().equals(ResponseEntity.class)) {
            return false;
        }

        // 获取当前请求 URI（通过外部变量传递）
        String requestUri = getCurrentRequestUri();

        if (requestUri == null) {
            return true;
        }

        // 排除 Swagger 和 Actuator 相关路径
        for (String path : EXCLUDE_PATHS) {
            if (requestUri.startsWith(path)) {
                return false;
            }
        }

        return true;
    }

    // 获取当前请求 URI
    private String getCurrentRequestUri() {
        try {
            // 获取当前线程的请求属性
            Object requestAttributes = RequestContextHolder.currentRequestAttributes();
            if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                return request != null ? request.getRequestURI() : null;
            }
        } catch (Exception e) {
            return null; // 如果无法获取请求对象，返回 null
        }
        return null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 特殊处理 String 类型，防止被包装成 JSON 导致响应格式错误
        if (body instanceof String) {
            return ResponseEntity.success(body).toString();
        }
        // 将原始返回值 body 包装成 ResponseEntity 格式
        return ResponseEntity.success(body);
    }
}
