package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dao.CommodityDao;
import com.example.ecommerce.entity.Commodity;
import com.example.ecommerce.service.CommodityService;
import com.example.ecommerce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
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


    @Override
    public List<Commodity> findByNameAndDate(String name, String startTime, String endTime) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CommodityDao mapper = sqlSession.getMapper(CommodityDao.class);
        List<Commodity> byNameAndDate=mapper.findByNameAndDate(name,startTime,endTime);
        sqlSession.commit();
        sqlSession.close();//关闭事务连接，持久化才能有效
        return byNameAndDate;
    }

    @Override
    public void addCommodity(Commodity commodity) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CommodityDao mapper = sqlSession.getMapper(CommodityDao.class);
        mapper.addCommodity(commodity);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Commodity commodity) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CommodityDao mapper = sqlSession.getMapper(CommodityDao.class);
        mapper.update(commodity);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CommodityDao mapper = sqlSession.getMapper(CommodityDao.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
