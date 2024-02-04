package com.example.ecommerce.entity.param;

import com.example.ecommerce.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Title: UserForm
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity.form
 * @Date 2024/1/17 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Integer id;

    @NotBlank(message="用户名不能为空")
    @Size(min=3,max=10,message = "用户名长度为4-10位")
    private  String username;//用户姓名

    @NotBlank
    @Size(min=6,max=12,message = "用户密码长度为6-12")
    private  String password;//用户密码

    @NotBlank
    private  String sex;//用户性别

    @NotBlank
    private  String  province;//用户所属省份

    @NotBlank
    private  String  city;//用户所属城市

    private LocalDateTime createdAt=LocalDateTime.now();//用户创建时间

    private List<Order> ordersList;

}
