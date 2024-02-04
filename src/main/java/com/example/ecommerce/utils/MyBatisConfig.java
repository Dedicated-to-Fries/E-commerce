package com.example.ecommerce.utils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: MybatisUtils
 * @Author 杨金鹏
 * @Package com.example.ecommerce.config
 * @Date 2023/12/12 15:35
 */
@Configuration
@MapperScan("com.example.ecommerce.dao")
public class MyBatisConfig{

}
