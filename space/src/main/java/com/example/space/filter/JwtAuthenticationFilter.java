package com.example.space.filter;

import com.example.space.config.SecurityProperties;
import com.example.space.enums.RoleEnum;
import com.example.space.service.UserService;
import com.example.space.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final SecurityProperties securityProperties;
    private final Environment environment;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService, SecurityProperties securityProperties, Environment environment) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.securityProperties = securityProperties;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String referer = request.getHeader("Referer");
        // 不需要认证的接口
        if (isPathAllowed(path, referer)) {
            // 设置匿名用户
            UsernamePasswordAuthenticationToken anonymousAuth = new UsernamePasswordAuthenticationToken(
                    "anonymousUser", null, AuthorityUtils.createAuthorityList(RoleEnum.ANONYMOUS.getAuthority())
            );
            SecurityContextHolder.getContext().setAuthentication(anonymousAuth);
            // 继续过滤链
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头中提取 token
        String token = extractToken(request);

        if (token != null) {
            // 验证 token 是否有效
            if (!jwtUtil.validateToken(token)) {
                throw new JwtException("无效的token");
            }

            // 解析 token 获取用户名
            String username = jwtUtil.getUsernameFromToken(token);
            // 根据用户名获取用户详情（角色等信息）
            UserDetails userDetails = userService.loadUserByUsername(username);

            // 创建认证对象
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 将认证对象存入 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

    private boolean isPathAllowed(String path, String referer) {
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
        return securityProperties.getAllowedRefererList() != null &&
                securityProperties.getAllowedRefererList().contains(referer);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
