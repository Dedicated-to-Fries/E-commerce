package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: Order
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity
 * @Date 2023/12/14 22:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;//下单用户的Id

    private String  number;//订单编号

    private String price;//实付款

    private String paymentMethods;//支付方式

    private String shippingMethods;//配送方式
    private List<Commodity>  commodities;//订单中包含商品信息

    public void addCommodity(Commodity commodity){
        this.commodities.add(commodity);
    }


}
