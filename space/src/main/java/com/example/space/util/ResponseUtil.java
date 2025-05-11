package com.example.space.util;

import com.example.space.model.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    /**
     * 响应JSON数据
     *
     * @param response 响应对象
     * @param message  响应消息
     * @param status   响应状态码
     * @throws IOException IO 异常
     */
    public static void writeJson(HttpServletResponse response, String message, int status) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(status);

        // 创建 ResponseEntity 对象，并设置状态码和消息
        ResponseEntity<?> responseBody = ResponseEntity.custom(status, message, null);
        // 显式序列化为JSON字符串
        String jsonResponse = new ObjectMapper().writeValueAsString(responseBody);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(jsonResponse);
            writer.flush();
        }
    }

}
