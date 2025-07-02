package com.example.space.util;

import com.example.space.config.PaginationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Component
public class RequestValidator {

    private final PaginationProperties paginationProperties;

    public RequestValidator(PaginationProperties paginationProperties) {
        this.paginationProperties = paginationProperties;
    }

    /**
     * 确保 pageIndex 大于等于指定或默认的最小页码。
     *
     * @param pageIndex    请求的页码，可为 null，此时使用默认值
     * @param minPageIndex 最小允许的页码，可为 null，此时使用配置的最小值
     * @return 合法化后的页码
     */
    public int validatePageIndex(Integer pageIndex, Integer minPageIndex) {
        int effectivePageIndex = getEffectiveValue(pageIndex, paginationProperties::getDefaultPageIndex);
        int effectiveMinPageIndex = getEffectiveValue(minPageIndex, paginationProperties::getMinPageIndex);
        if (effectiveMinPageIndex < 0) {
            throw new IllegalArgumentException("最小页码必须至少为0");
        }
        return Math.max(effectiveMinPageIndex, effectivePageIndex);
    }

    /**
     * 确保 pageIndex 大于等于默认的最小页码。
     *
     * @param pageIndex 请求的页码，可为 null，此时使用默认值
     * @return 合法化后的页码
     */
    public int validatePageIndex(Integer pageIndex) {
        return this.validatePageIndex(pageIndex, null);
    }

    /**
     * 确保 pageSize 在指定或默认的最小和最大页面大小之间。
     *
     * @param pageSize    请求的页面大小，可为 null，此时使用默认值
     * @param minPageSize 最小允许的页面大小，可为 null，此时使用配置的最小值
     * @param maxPageSize 最大允许的页面大小，可为 null，此时使用配置的最大值
     * @return 合法化后的页面大小
     */
    public int validatePageSize(Integer pageSize, Integer minPageSize, Integer maxPageSize) {
        int effectivePageSize = getEffectiveValue(pageSize, paginationProperties::getDefaultPageSize);
        int effectiveMinPageSize = getEffectiveValue(minPageSize, paginationProperties::getMinPageSize);
        int effectiveMaxPageSize = getEffectiveValue(maxPageSize, paginationProperties::getMaxPageSize);
        if (effectiveMinPageSize < 1 || effectiveMaxPageSize < 1 || effectiveMinPageSize > effectiveMaxPageSize) {
            throw new IllegalArgumentException("页面大小范围无效：min=" + effectiveMinPageSize + ", max=" + effectiveMaxPageSize);
        }
        return Math.min(Math.max(effectiveMinPageSize, effectivePageSize), effectiveMaxPageSize);
    }

    /**
     * 确保 pageSize 在默认的最小和最大页面大小之间。
     *
     * @param pageSize 请求的页面大小，可为 null，此时使用默认值
     * @return 合法化后的页面大小
     */
    public int validatePageSize(Integer pageSize) {
        return this.validatePageSize(pageSize, null, null);
    }

    /**
     * 防止 List 为 null，转为空列表
     *
     * @param list List
     * @return List
     */
    public <T> List<T> validateList(List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }

    /**
     * 防止字符串为 null，转为空字符串
     *
     * @param str 字符串
     * @return 字符串
     */
    public String validateString(String str) {
        return Objects.requireNonNullElseGet(str, () -> "");
    }

    /**
     * 校验布尔值，默认 false
     *
     * @param bool 布尔值
     * @return 布尔值，如果为 null 返回 false
     */
    public boolean validateBoolean(Boolean bool) {
        // Boolean.TRUE.equals(null) 返回 false，安全处理 null
        return Boolean.TRUE.equals(bool);
    }

    private int getEffectiveValue(Integer value, Supplier<Integer> defaultValueSupplier) {
        return Objects.requireNonNullElseGet(value, defaultValueSupplier);
    }

}
