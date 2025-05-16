package com.example.space.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public final class WebUtils {

    private WebUtils() {
        // 工具类不允许实例化
    }

    /**
     * 从 HttpServletRequest 中提取 Bearer Token
     * @param request HttpServletRequest 对象
     * @return 返回提取的 Bearer Token，如果未找到则返回 null
     */
    public static String extractBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
