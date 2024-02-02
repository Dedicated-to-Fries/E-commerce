package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Commodity
 * @Author 杨金鹏
 * @Package com.example.ecommerce.entity
 * @Date 2023/12/12 21:56
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity implements Serializable {

    private Integer id;//商品id

    private String name;//商品名称

    private String type;//商品分类

    private String sales;//实际销量

    private String ordering;//排序

    private String status;//商品状态

    private String url;//图片访问路径

    private  Date createdAt=new Date();//商品创建日期

}
