package com.example.space.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 只启用 JSON 内容协商，避免模板引擎干扰 API 响应。不配置这个会导致在抛出异常时，请求路径会重复拼接
        registry.enableContentNegotiation(new MappingJackson2JsonView());
    }

}
