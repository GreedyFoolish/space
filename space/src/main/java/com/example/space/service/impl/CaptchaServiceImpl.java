package com.example.space.service.impl;

import com.example.space.config.CaptchaProperties;
import com.example.space.service.CaptchaGenerator;
import com.example.space.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(CaptchaGeneratorImpl.class);
    // Redis 模板
    private final StringRedisTemplate redisTemplate;
    // 验证码配置
    private final CaptchaProperties captchaProperties;
    // 验证码生成器
    private final CaptchaGenerator captchaGenerator;

    public CaptchaServiceImpl(StringRedisTemplate redisTemplate, CaptchaProperties captchaProperties, CaptchaGenerator captchaGenerator) {
        this.redisTemplate = redisTemplate;
        this.captchaProperties = captchaProperties;
        this.captchaGenerator = captchaGenerator;
    }

    @Override
    public String generateCaptchaCode() {
        return captchaGenerator.generateRandomCaptcha();
    }

    @Override
    public BufferedImage generateCaptchaImage(String captcha) {
        return captchaGenerator.generateCaptchaImage(captcha);
    }

    @Override
    public void saveCaptcha(String key, String captcha) {
        redisTemplate.opsForValue().set("captcha:" + key, captcha, captchaProperties.getExpiration(), TimeUnit.MINUTES);
    }

    @Override
    public boolean validateCaptcha(String key, String inputCaptcha) {
        if (key == null || inputCaptcha == null) {
            return false;
        }
        String captcha = redisTemplate.opsForValue().get("captcha:" + key);
        return captcha != null && captcha.equalsIgnoreCase(inputCaptcha);
    }

}
