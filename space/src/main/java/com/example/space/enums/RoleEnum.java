package com.example.space.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ANONYMOUS("ROLE_ANONYMOUS", 0), // 匿名用户
    USER("ROLE_USER", 1000), // 普通用户
    ADMIN("ROLE_ADMIN", 2000), // 管理员
    SUPER_ADMIN("ROLE_SUPER_ADMIN", 3000); // 超级管理员

    private final String authority;
    private final int authCode;

    RoleEnum(String authority, int authCode) {
        this.authority = authority;
        this.authCode = authCode;
    }

    /**
     * 根据权限代码获取对应的枚举值
     *
     * @param code 权限代码
     * @return 枚举值
     */
    public static RoleEnum fromAuthCode(int code) {
        for (RoleEnum role : values()) {
            if (role.getAuthCode() == code) {
                return role;
            }
        }
        return ANONYMOUS;
    }

    // 根据权限字符串获取对应的枚举值

    /**
     * 根据权限字符串获取对应的枚举值
     *
     * @param authority 权限字符串
     * @return 枚举值
     */
    public static RoleEnum fromAuthority(String authority) {
        for (RoleEnum role : values()) {
            if (role.getAuthority().equals(authority)) {
                return role;
            }
        }
        return ANONYMOUS;
    }

}
