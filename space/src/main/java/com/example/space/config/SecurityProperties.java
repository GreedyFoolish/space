package com.example.space.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    // 允许的跨域的 referer 来源
    private List<String> allowedRefererList = new ArrayList<>();
    // 允许的跨域的地址来源
    private List<String> allowedOriginList = new ArrayList<>();
    // 拦截器排除的路径
    private List<String> excludePaths;

}
