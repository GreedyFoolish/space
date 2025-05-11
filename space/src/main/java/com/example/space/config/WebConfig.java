package com.example.space.config;

import com.example.space.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    /**
     * 配置内容协商视图解析器
     *
     * @param registry 视图解析器注册器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 只启用 JSON 内容协商，避免模板引擎干扰 API 响应。不配置这个会导致在抛出异常时，请求路径会重复拼接
        registry.enableContentNegotiation(new MappingJackson2JsonView());
    }

    /**
     * 将登录拦截器添加到 Spring MVC 的拦截器链中
     * 拦截所有请求，除了静态资源、登录页面、API 文档和 Swagger UI 的请求
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/login", "/v3/api-docs/**", "/swagger-ui/**");
    }

}
