package com.example.space.enums;

import lombok.Getter;

@Getter
public enum BooleanEnum {
    TRUE(true, 1),
    FALSE(false, 0);

    private final Boolean key;
    private final Integer value;

    BooleanEnum(Boolean key, Integer value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 将 Boolean 转为 Integer
     */
    public static Integer toInteger(Boolean bool) {
        return bool != null && bool ? TRUE.value : FALSE.value;
    }

    /**
     * 将 Integer 转为 Boolean
     */
    public static Boolean toBoolean(Integer value) {
        return value != null && value.equals(TRUE.value);
    }

}
