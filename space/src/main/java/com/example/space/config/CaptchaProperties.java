package com.example.space.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.captcha")
@Getter
@Setter
public class CaptchaProperties {

    // 验证码过期时间，单位分钟
    private int expiration;

}
