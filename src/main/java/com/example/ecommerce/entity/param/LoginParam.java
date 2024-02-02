package com.example.ecommerce.entity.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Title: loginParam
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity.param
 * @Date 2024/1/26 18:33
 */

@Data
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message="用户名不能为空")
    private  String username;//用户姓名

    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 12, message = "密码长度为4-12位")
    private  String password;//用户密码

}
