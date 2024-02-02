package com.example.ecommerce.service;

import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.entity.param.LoginParam;
import com.example.ecommerce.entity.param.UserParam;
import com.example.ecommerce.entity.vo.UserVO;

/**
 * @Title: UserDao
 * @Author 杨金鹏
 * @Package com.example.ecommerce.dao
 * @Date 2023/12/30 20:37
 */

public interface UserService {

    void register(UserParam user);

    UserEntity findByName(String username);

    UserVO login(LoginParam user);
}
