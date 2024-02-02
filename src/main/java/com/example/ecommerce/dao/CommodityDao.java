package com.example.ecommerce.dao;

import com.example.ecommerce.entity.Commodity;

import java.util.List;

/**
 * @Title: CommodityDao
 * @Author 杨金鹏
 * @Package com.example.ecommerce.dao
 * @Date 2023/12/12 22:27
 */
public interface CommodityDao {

    List<Commodity>  findByNameAndDate(String name, String startTime, String endTime);

    Boolean addCommodity(Commodity commodity);

    Boolean update(Commodity commodity);

    Boolean deleteById(Integer id);



}
