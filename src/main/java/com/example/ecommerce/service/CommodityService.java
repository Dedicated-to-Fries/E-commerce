package com.example.ecommerce.service;

import com.example.ecommerce.entity.Commodity;

import java.util.List;

/**
 * @Title: CommodityDao
 * @Author 杨金鹏
 * @Package com.example.ecommerce.dao
 * @Date 2023/12/12 22:27
 */
public interface CommodityService {

    List<Commodity>  findByNameAndDate(String name, String startTime, String endTime);

    void addCommodity(Commodity commodity);

    void update(Commodity commodity);

    void deleteById(Integer id);



}
