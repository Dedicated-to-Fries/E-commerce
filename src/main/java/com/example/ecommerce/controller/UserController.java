package com.example.ecommerce.controller;

import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.entity.param.LoginParam;
import com.example.ecommerce.entity.param.UserParam;
import com.example.ecommerce.entity.vo.UserVO;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: UserController
 * @Author 杨金鹏
 * @Package com.example.ecommerce.controller
 * @Date 2023/12/31 17:39
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;



    /**
     * @apiNote 注册用户
     * @param  user 用户
     */
    @PostMapping("/register")
    public String register(@RequestBody @Validated UserParam user)  {
        userService.register(user);
        return "注册成功";
    }

    @GetMapping("/findByName")
    public UserEntity findByName(String username){
        UserEntity byName = userService.findByName(username);
        return byName;
    }

    @PostMapping("/login")
    public UserVO login(@RequestBody @Validated LoginParam userParam){
        return userService.login(userParam);
    }

}
