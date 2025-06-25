package com.example.space.filter;

import com.example.space.enums.RoleEnum;
import com.example.space.security.PathAllowChecker;
import com.example.space.service.SpaceUserService;
import com.example.space.util.JwtUtil;
import com.example.space.util.WebUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SpaceUserService spaceUserService;
    private final PathAllowChecker pathAllowChecker;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, SpaceUserService spaceUserService, PathAllowChecker pathAllowChecker) {
        this.jwtUtil = jwtUtil;
        this.spaceUserService = spaceUserService;
        this.pathAllowChecker = pathAllowChecker;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 不需要认证的接口
        if (pathAllowChecker.isPathWhitelisted(request)) {
            // 设置匿名用户
            UsernamePasswordAuthenticationToken anonymousAuth = new UsernamePasswordAuthenticationToken(
                "anonymousUser", null, AuthorityUtils.createAuthorityList(RoleEnum.ANONYMOUS.getAuthority())
            );
            // 将匿名用户存入 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(anonymousAuth);
            // 继续过滤链
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头中提取 token
        String token = WebUtils.extractBearerToken(request);

        if (token != null) {
            // 验证 token 是否有效
            if (!jwtUtil.validateToken(token)) {
                throw new JwtException("无效的token");
            }
            // 解析 token 获取用户名
            String username = jwtUtil.getUsernameFromToken(token);
            // 根据用户名获取用户详情（角色等信息）
            UserDetails userDetails = spaceUserService.loadUserByUsername(username);
            // 创建认证对象
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
            );
            // 设置认证详情
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将认证对象存入 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

}
