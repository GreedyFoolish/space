package com.example.space.model;

import com.example.space.enums.ResponseCodeEnum;
import com.example.space.exception.BusinessException;
import com.example.space.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Schema(description = "统一响应结构")
public class ResponseEntity<T> {
    @Schema(description = "响应状态码", example = "200")
    private int code;
    @Schema(description = "响应消息", example = "success")
    private String message;
    @Schema(description = "响应数据")
    private T data;
    private static final Logger logger = LoggerFactory.getLogger(ResponseEntity.class);
    private static final int SUCCESS_CODE = ResponseCodeEnum.SUCCESS.getCode();
    private static final String SUCCESS_MESSAGE = ResponseCodeEnum.SUCCESS.getMessage();
    private static final int FAILURE_CODE = ResponseCodeEnum.INTERNAL_SERVER_ERROR.getCode();

    // 无参构造函数
    public ResponseEntity() {
    }

    // 带参数的构造函数
    public ResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        if (logger.isDebugEnabled()) { // 检查是否启用了 debug 日志
            logger.debug("Response created: Code={}, Message={}, Data={}", code, message, data);
        }
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ResponseEntity<T> failure(String message) {
        return new ResponseEntity<>(FAILURE_CODE, message, null);
    }

    public static <T> ResponseEntity<T> custom(int code, String message, T data) {
        return new ResponseEntity<>(code, message, data);
    }

    public static Map<String, Object> fromBusinessException(BusinessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        response.put("data", ex.getData());
        return response;
    }

    public static Map<String, Object> fromResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());
        response.put("data", null);
        return response;
    }

    public static Map<String, Object> fromMethodArgumentNotValidException(int code, String message, Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();

        // 拼接错误信息
        StringBuilder messageBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            messageBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("; ");
        }

        String finalMessage = messageBuilder.toString();
        if (finalMessage.endsWith("; ")) {
            finalMessage = finalMessage.substring(0, finalMessage.length() - 2);
        }

        response.put("code", code);
        response.put("message", finalMessage);
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> customMessage(int code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

}
