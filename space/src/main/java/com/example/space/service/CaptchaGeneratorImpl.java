package com.example.space.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CaptchaGeneratorImpl implements CaptchaGenerator {

    @Override
    public String generateRandomCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }

    @Override
    public BufferedImage generateCaptchaImage(String text) {
        int width = 120;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 绘制干扰线
        for (int i = 0; i < 30; i++) {
            g.setColor(getRandColor(130, 200));
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int xl = (int) (Math.random() * 10);
            int yl = (int) (Math.random() * 10);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 绘制验证码
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        g.setColor(getRandColor(160, 200));
        g.drawString(text, 10, 25);

        g.dispose();
        return image;
    }

    private Color getRandColor(int fc, int bc) {
        Random rand = new Random();
        fc = Math.min(255, fc);
        bc = Math.min(255, bc);
        int r = fc + rand.nextInt(bc - fc);
        int g = fc + rand.nextInt(bc - fc);
        int b = fc + rand.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
