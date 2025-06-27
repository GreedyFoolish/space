package com.example.space.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public enum StatusEnum {

    ENABLED("启用", 0),
    DISABLED("禁用", 1);

    private final String desc;
    private final Integer value;

    StatusEnum(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }

    /**
     * 根据状态码获取枚举对象
     */
    public static StatusEnum fromValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (StatusEnum status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 将 Boolean 转换为状态码（true -> ENABLED(0), false -> DISABLED(1)）
     */
    public static Integer toInteger(Boolean bool) {
        return bool != null && bool ? ENABLED.value : DISABLED.value;
    }

    /**
     * 将状态码转换为 Boolean（0 -> true, 1 -> false）
     */
    public static Boolean toBoolean(Integer value) {
        return value != null && value.equals(ENABLED.value);
    }

    /**
     * 批量转换 List<Boolean> -> List<Integer>
     */
    public static List<Integer> toIntegers(List<Boolean> booleans) {
        if (booleans == null) {
            return Collections.emptyList();
        }

        return booleans.stream()
            .map(StatusEnum::toInteger)
            .toList();
    }

    /**
     * 批量转换 List<Integer> -> List<Boolean>
     */
    public static List<Boolean> toBooleans(List<Integer> integers) {
        if (integers == null) {
            return Collections.emptyList();
        }

        return integers.stream()
            .map(StatusEnum::toBoolean)
            .toList();
    }

}
