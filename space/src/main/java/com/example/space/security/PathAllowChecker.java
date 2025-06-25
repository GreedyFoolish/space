package com.example.space.security;

import com.example.space.config.SecurityProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PathAllowChecker {

    private final Environment environment;
    private final SecurityProperties securityProperties;

    public PathAllowChecker(Environment environment, SecurityProperties securityProperties) {
        this.environment = environment;
        this.securityProperties = securityProperties;
    }

    public boolean isPathWhitelisted(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        String referer = request.getHeader("Referer");
        // 去除上下文路径后进行判断
        if (path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }
        // 始终允许的路径
        if (path.startsWith("/api/auth/") || path.startsWith("/api/user/register")) {
            return true;
        }
        // 只在 dev 环境下启用 referer 白名单
        boolean isDev = environment.acceptsProfiles(Profiles.of("dev"));
        // 如果当前环境不是 dev，则不允许任何 referer
        if (!isDev) {
            return false;
        }
        // 判断 referer 是否在白名单中
        List<String> allowedRefererList = securityProperties.getAllowedRefererList();
        return allowedRefererList != null && allowedRefererList.contains(referer);
    }

}
