package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dao.OrderDao;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: OrderServicelmpl
 * @Author 杨金鹏
 * @Package com.example.ecommerce.service
 * @Date 2024/1/3 23:23
 */
@Service
public class OrderServicelmpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public List<UserEntity> oneToManyByUser(int userId) {
        List<UserEntity> users = orderDao.oneToManyByUser(userId);
        return users;
    }
}
