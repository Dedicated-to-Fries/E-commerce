package com.example.ecommerce.exception;

import lombok.Getter;

/**
 * @Title: ResultCode
 * @Author 杨金鹏
 * @Package com.example.ecommerce.security.plugin
 * @Date 2024/1/29 17:19
 */


/**
 * @author RudeCrab
 * @description 响应码枚举
 */
@Getter
public enum ResultCode {

   SUCCESS(0000, "操作成功"),

    UNAUTHORIZED(1001, "没有登录"),

    FORBIDDEN(1002, "用户名已存在，无法注册"),

    VALIDATE_FAILED(1003, "参数校验失败"),

    FAILED(1004, "接口异常"),

    ERROR(5000, "账号或密码错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
