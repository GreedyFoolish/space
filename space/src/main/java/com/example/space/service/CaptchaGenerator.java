package com.example.space.service;

import java.awt.image.BufferedImage;

public interface CaptchaGenerator {

    /**
     * 生成随机验证码
     *
     * @return 验证码
     */
    String generateRandomCaptcha();

    /**
     * 生成验证码图片
     *
     * @param captchaText 验证码文本
     * @return 验证码图片
     */
    BufferedImage generateCaptchaImage(String captchaText);

}
