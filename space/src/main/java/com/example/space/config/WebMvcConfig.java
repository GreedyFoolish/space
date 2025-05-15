package com.example.space.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     *
     * @param registry cors注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
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
