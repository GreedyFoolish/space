package com.example.space.service;

import java.awt.image.BufferedImage;

public interface CaptchaGenerator {

    String generateRandomCaptcha();

    BufferedImage generateCaptchaImage(String captchaText);

}
