package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dao.OrderDao;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
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


    @Override
    public void addOrder(Order order) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao mapper = sqlSession.getMapper(OrderDao.class);
        mapper.addOrder(order);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<UserEntity> oneToManyByUser(int userId) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao mapper = sqlSession.getMapper(OrderDao.class);
        List<UserEntity> users = mapper.oneToManyByUser(userId);
        sqlSession.commit();
        sqlSession.close();
        return users;
    }
}
