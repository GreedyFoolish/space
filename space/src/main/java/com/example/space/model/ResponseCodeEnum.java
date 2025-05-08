package com.example.space.model;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    SUCCESS(200, "Success"), // 请求成功
    CREATED(201, "Created"), // 资源创建成功
    ACCEPTED(202, "Accepted"), // 请求被接受，但尚未处理完成
    NO_CONTENT(204, "No Content"), // 成功处理，但无内容返回

    BAD_REQUEST(400, "Bad Request"), // 客户端请求的语法错误
    UNAUTHORIZED(401, "Unauthorized"), // 未授权
    FORBIDDEN(403, "Forbidden"), // 禁止访问
    NOT_FOUND(404, "Not Found"), // 资源未找到
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"), // 请求方法不被允许
    REQUEST_TIMEOUT(408, "Request Timeout"), // 请求超时
    CONFLICT(409, "Conflict"), // 请求冲突
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"), // 不支持的媒体类型

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"), // 服务器内部错误
    NOT_IMPLEMENTED(501, "Not Implemented"), // 服务器不支持请求功能
    SERVICE_UNAVAILABLE(503, "Service Unavailable"), // 服务不可用
    GATEWAY_TIMEOUT(504, "Gateway Timeout"), // 网关超时

    CUSTOM_ERROR_1001(1001, "Parameter Error"), // 参数错误
    CUSTOM_ERROR_1002(1002, "User Not Logged In"), // 用户未登录
    CUSTOM_ERROR_1003(1003, "Permission Denied"), // 权限不足
    CUSTOM_ERROR_1004(1004, "Resource Not Found"), // 资源未找到
    CUSTOM_ERROR_1005(1005, "Operation Failed"), // 操作失败
    CUSTOM_ERROR_1006(1006, "System Busy"), // 系统繁忙
    CUSTOM_ERROR_1007(1007, "Invalid Token"), // 无效的Token
    CUSTOM_ERROR_1008(1008, "Expired Token"); // Token已过期

    private final int code;
    private final String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
