package com.example.ecommerce.entity.vo;

import com.example.ecommerce.exception.ResultCode;
import lombok.Getter;

/**
 * @Title: ResultVo
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity.vo
 * @Date 2024/1/30 16:26
 */

@Getter
public class  ResultVO<T> {
    /**
     * 状态码, 默认1000是成功
     */
    private int code;
    /**
     * 响应信息, 来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":\"%s\"}", code, msg, data.toString());
    }

}
