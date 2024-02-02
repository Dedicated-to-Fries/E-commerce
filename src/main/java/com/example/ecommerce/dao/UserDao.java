package com.example.ecommerce.dao;

import com.example.ecommerce.entity.UserEntity;

/**
 * @Title: UserDao
 * @Author 杨金鹏
 * @Package com.example.ecommerce.dao
 * @Date 2023/12/30 20:37
 */

public interface UserDao {

    Boolean register(UserEntity user);

    UserEntity findByName(String username);
}
