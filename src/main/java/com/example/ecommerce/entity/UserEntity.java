package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Title: User
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity
 * @Date 2023/12/14 23:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    private  Integer id;//用户id

    private  String username;//用户姓名

    private  String password;//用户密码

    private  String sex;//用户性别

    private  String  province;//用户所属省份

    private  String  city;//用户所属城市

    private LocalDateTime createdAt=LocalDateTime.now();//用户创建时间

    private List<Order>  ordersList;


}
