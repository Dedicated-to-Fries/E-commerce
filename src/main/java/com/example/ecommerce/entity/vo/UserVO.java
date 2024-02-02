package com.example.ecommerce.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Title: UserVo
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity.vo
 * @Date 2024/1/28 15:38
 */

@Data
@Accessors(chain = true)
public class UserVO {
    private Integer id;//用户ID

    private String username;//用户名

    private String token;//登录认证token

}
