package com.example.ecommerce.controller;

import com.example.ecommerce.entity.vo.ResultVO;
import com.example.ecommerce.exception.ApiException;
import com.example.ecommerce.exception.ResultCode;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: 全局异常处理
 * @Author 杨金鹏
 * @Package com.example.ecommerce.controller
 * @Date 2024/1/30 16:41
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResultVO<String> apiExceptionHandler(ApiException e) {
        return new  ResultVO<>(e.getResultCode(), e.getMsg());
    }


    /**
     * 对参数进行校验，捕获因校验失败的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

}
