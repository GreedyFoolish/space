package com.example.space.config;

import com.example.space.enums.RoleEnum;
import com.example.space.service.UserService;
import com.example.space.util.JwtUtil;
import com.example.space.interceptor.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final SecurityProperties securityProperties;
    private final Environment environment;
    private final String[] SWAGGER_PATHS = new String[]{
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/**"
    };

    @Autowired
    public SecurityConfig(JwtUtil jwtUtil, UserService userService, SecurityProperties securityProperties, Environment environment) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.securityProperties = securityProperties;
        this.environment = environment;
    }

    /**
     * 配置过滤器链
     * 在开发阶段，如果需要使用 swagger 进行接口测试，将 hasAnyAuthority() 替换为 permitAll()，或添加匿名用户权限
     *
     * @param http HttpSecurity
     * @return 安全过滤链
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SWAGGER_PATHS).permitAll()
                        .requestMatchers("/api/auth", "/api/user/register").permitAll()
                        .requestMatchers("/api/user/**").hasAnyAuthority(
                                RoleEnum.ANONYMOUS.getAuthority(), // 匿名用户
                                RoleEnum.USER.getAuthority(), // 普通用户
                                RoleEnum.ADMIN.getAuthority(), // 管理员
                                RoleEnum.SUPER_ADMIN.getAuthority() // 超级管理员
                        )
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userService, securityProperties, environment), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 密码加密器
     *
     * @return 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *
     * @param config 身份验证配置
     * @return 身份验证管理器
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
