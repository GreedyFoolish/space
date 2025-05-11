package com.example.space.enums;

import lombok.Getter;

@Getter
public enum AuthCode {

    // 管理员权限
    MANAGER(1000, "/login"),
    // 用户权限
    USER(2000, "/user/login"),
    // 游客权限
    GUEST(3000, "/guest");

    private final int id;
    private final String url;

    AuthCode(int id, String url) {
        this.id = id;
        this.url = url;
    }

}
