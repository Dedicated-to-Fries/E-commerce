package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dao.CommodityDao;
import com.example.ecommerce.entity.Commodity;
import com.example.ecommerce.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: StudentServiceImpl
 * @Author 杨金鹏
 * @Package com.example.ecommerce.service
 * @Date 2023/12/12 16:35
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    @Override
    public List<Commodity> findByNameAndDate(String name, String startTime, String endTime) {
        List<Commodity> byNameAndDate=commodityDao.findByNameAndDate(name,startTime,endTime);
        return byNameAndDate;
    }

    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.addCommodity(commodity);
    }

    @Override
    public void update(Commodity commodity) {
        commodityDao.update(commodity);
    }

    @Override
    public void deleteById(Integer id) {
        commodityDao.deleteById(id);
    }
}
