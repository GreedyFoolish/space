package com.example.space.service;

import com.example.space.config.CaptchaProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService {

    private final StringRedisTemplate redisTemplate;
    private final CaptchaProperties captchaProperties;
    private final CaptchaGenerator captchaGenerator;

    public CaptchaService(StringRedisTemplate redisTemplate, CaptchaProperties captchaProperties, CaptchaGenerator captchaGenerator) {
        this.redisTemplate = redisTemplate;
        this.captchaProperties = captchaProperties;
        this.captchaGenerator = captchaGenerator;
    }

    public String generateCaptchaCode() {
        return captchaGenerator.generateRandomCaptcha();
    }

    public BufferedImage generateCaptchaImage(String captcha) {
        return captchaGenerator.generateCaptchaImage(captcha);
    }

    public void saveCaptcha(String key, String captcha) {
        redisTemplate.opsForValue().set("captcha:" + key, captcha, captchaProperties.getExpiration(), TimeUnit.MINUTES);
    }

    public boolean validateCaptcha(String key, String inputCaptcha) {
        if (key == null || inputCaptcha == null) {
            return false;
        }
        String captcha = redisTemplate.opsForValue().get("captcha:" + key);
        return captcha != null && captcha.equalsIgnoreCase(inputCaptcha);
    }

}
