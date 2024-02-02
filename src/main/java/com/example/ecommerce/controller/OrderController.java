package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: OrderController
 * @Author 杨金鹏
 * @Package com.example.ecommerce.controller
 * @Date 2024/1/3 23:26
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public RabbitTemplate  rabbitTemplate;

    @Autowired
    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @apiNote 模拟查询订单详情
     * @param userId 实体
     */

    @GetMapping("/detail")
    public String  OrderDetail(int userId){
        /**
         * 模拟用户在购物车中所添加的商品，实际业务则需要查询当前用户、购物车中已被勾选的商品，以及还有一些重要的业务逻辑:
         * 1.可以通过UserFilter过滤器类进行登录校验，登录成功后，才能查询当前用户、以及购物车、商品状态
         * 2.判断商品是否存在；如果存在，是否是上架状态；商品库存是否足够，库存减少等；
         * 3.随机生成一个订单号
         * 4.管理订单状态类
         * 5.取消订单
         */
        List<UserEntity> users = orderService.oneToManyByUser(userId);//根据获得的用户id查询出该用户下的所有订单
        UserEntity user = users.get(0);
        List<Order> ordersList = user.getOrdersList();
        return  "查询到的用户信息为："+user.getUsername()+"\n该用户下的订单有："+ordersList;
    }



    /**
     * @apiNote 模拟商品下单
     * @param order 实体
     */
    @PostMapping("/SendOrderMessage")
    public String SendOrder(@RequestBody Order order){
        /**
         * 模拟下单后，将用户下的订单信息同步到另一个App(工程2)
         * 使用RabbitMQ
         */
        orderService.addOrder(order);
        int userId = order.getUserId();//查询到的订单用户id
        List<UserEntity> users = orderService.oneToManyByUser(userId);//通过订单下的用户id查询找用户下的所有订单
        UserEntity user = users.get(0);
        rabbitTemplate.convertAndSend("TopicExchange","firstQueue",user);//发送订单到APP，绑定建为topic.key
        return "下单完成并发送成功";
    }

}
