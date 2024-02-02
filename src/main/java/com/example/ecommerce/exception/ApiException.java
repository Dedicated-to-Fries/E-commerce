package com.example.ecommerce.exception;

import lombok.Getter;

/**
 * @Title: ApiException
 * @Author 杨金鹏
 * @Package com.example.ecommerce.security.plugin
 * @Date 2024/1/29 17:07
 */


/**
 * @author RudeCrab
 * @description 自定义异常
 */
@Getter
public class ApiException extends RuntimeException{
    private final String msg;
    private final ResultCode resultCode;

    public ApiException() {
        this(ResultCode.FAILED);
    }

    public ApiException(String msg) {
        this(ResultCode.FAILED, msg);
    }

    public ApiException(ResultCode resultCode) {
        this(resultCode, resultCode.getMsg());
    }

    public ApiException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
        this.msg =  msg;
    }
}
