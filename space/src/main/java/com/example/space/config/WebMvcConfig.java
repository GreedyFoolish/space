package com.example.space.config;

import com.example.space.interceptor.AuthInterceptor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 日志记录器
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(WebMvcConfig.class);
    private final AuthInterceptor authInterceptor;
    private final SecurityProperties securityProperties;

    public WebMvcConfig(AuthInterceptor authInterceptor, SecurityProperties securityProperties) {
        this.authInterceptor = authInterceptor;
        this.securityProperties = securityProperties;
    }

    /**
     * 跨域配置
     *
     * @param registry cors注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> excludePathsList = Optional.ofNullable(securityProperties.getAllowedOriginList())
            .orElse(Collections.emptyList());
        String[] originsPaths = excludePathsList.toArray(String[]::new);
        logger.debug("添加跨域配置，允许的域：{}", Arrays.toString(originsPaths));
        registry.addMapping("/api/**")
            .allowedOrigins(originsPaths)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathsList = Optional.ofNullable(securityProperties.getExcludePaths())
            .orElse(Collections.emptyList());
        String[] excludePaths = excludePathsList.toArray(String[]::new);
        logger.debug("添加拦截器，排除路径：{}", Arrays.toString(excludePaths));
        registry.addInterceptor(authInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns(excludePaths);
    }

    /**
     * 配置视图解析器
     *
     * @param registry 视图注册器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 只启用 JSON 内容协商，避免模板引擎干扰 API 响应。不配置这个会导致在抛出异常时，请求路径会重复拼接
        registry.enableContentNegotiation(new MappingJackson2JsonView());
    }

}
