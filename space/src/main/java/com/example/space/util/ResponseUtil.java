package com.example.space.util;

import com.example.space.model.ResponseEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ResponseUtil {

    private void ResponseUtils() {
        // 私有构造器防止实例化
    }

    /**
     * 写入统一格式的 JSON 错误响应
     *
     * @param response HttpServletResponse
     * @param status   HTTP 状态码
     * @param message  错误信息
     * @throws IOException IO 异常
     */
    public static void writeErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        String json = ResponseEntity.serialize(ResponseEntity.custom(status, message, null));
        response.getWriter().write(json);
    }

}
