package com.example.ecommerce.dao;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.UserEntity;

import java.util.List;

/**
 * @Title: OrderDao
 * @Author 杨金鹏
 * @Package com.example.ecommerce.dao
 * @Date 2024/1/3 18:37
 */
public interface OrderDao {

    Boolean addOrder(Order order);//用户下单

    List<UserEntity>  oneToManyByUser(int userId);//实现用户与订单一对多的关系，可查询到多个信息
}
