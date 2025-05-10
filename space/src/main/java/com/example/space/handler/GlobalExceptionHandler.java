package com.example.space.handler;

import com.example.space.exception.BusinessException;
import com.example.space.exception.ResourceNotFoundException;
import com.example.space.model.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 排除路径
    private final String[] excludePaths = {
            "/v3/api-docs",
            "/swagger-ui",
            "/actuator"
    };

    // 正则模式数组（用于更精确匹配）
    private final Pattern[] excludePathPatterns;

    public GlobalExceptionHandler() {
        // 初始化正则模式
        this.excludePathPatterns = new Pattern[excludePaths.length];
        for (int i = 0; i < excludePaths.length; i++) {
            excludePathPatterns[i] = Pattern.compile("^" + Pattern.quote(excludePaths[i]));
        }
    }

    // 统一业务异常
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        logger.error("业务逻辑异常：{}", ex.getMessage(), ex);
        return ResponseEntity.fromBusinessException(ex);
    }

    // 资源未找到
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("资源未找到：{}", ex.getMessage(), ex);
        return ResponseEntity.fromResourceNotFoundException(ex);
    }

    // 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        logger.error("参数验证失败：{}", errors);
        return ResponseEntity.failure("参数验证失败： " + errors);
    }

    // 运行时异常兜底
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        logger.error("运行时异常：{}", ex.getMessage(), ex);
        return ResponseEntity.failure("内部服务器错误");
    }

    // 所有其他异常兜底
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        logger.error("未知异常：{}", ex.getMessage(), ex);
        return ResponseEntity.failure("发生系统错误");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果返回类型是 ResponseEntity 或其子类，则不再包装
        if (ResponseEntity.class.isAssignableFrom(returnType.getParameterType())) {
            return false;
        }

        String requestUri = getCurrentRequestUri();

        // 如果无法获取 URI，默认不拦截
        if (requestUri == null) {
            return true;
        }

        // 排除特定路径格式
        for (Pattern pattern : excludePathPatterns) {
            if (pattern.matcher(requestUri).find()) {
                return false;
            }
        }

        return true;
    }

    // 获取当前请求 URI
    private String getCurrentRequestUri() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            return request != null ? request.getRequestURI() : null;
        } catch (Exception e) {
            logger.warn("无法获取当前请求 URI，可能是异步调用或非 Web 请求", e);
            return null;
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 特殊处理 String 类型，防止被包装成 JSON 导致响应格式错误
        if (body instanceof String) {
            return ResponseEntity.success(body);
        }
        // 将原始返回值 body 包装成 ResponseEntity 格式
        return ResponseEntity.success(body);
    }
}
