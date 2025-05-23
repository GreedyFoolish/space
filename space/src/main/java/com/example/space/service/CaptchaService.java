package com.example.space.service;

import java.awt.image.BufferedImage;

public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @return 验证码
     */
    String generateCaptchaCode();

    /**
     * 生成验证码图片
     *
     * @param captcha 验证码
     * @return 验证码图片
     */
    BufferedImage generateCaptchaImage(String captcha);

    /**
     * 保存验证码
     *
     * @param key     验证码 key
     * @param captcha 验证码
     */
    void saveCaptcha(String key, String captcha);

    /**
     * 验证验证码
     *
     * @param key          验证码 key
     * @param inputCaptcha 用户输入的验证码
     * @return 是否验证成功
     */
    boolean validateCaptcha(String key, String inputCaptcha);

}
