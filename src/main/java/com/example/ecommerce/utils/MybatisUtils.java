package com.example.ecommerce.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: MybatisUtils
 * @Author 杨金鹏
 * @Package com.example.ecommerce.config
 * @Date 2023/12/12 15:35
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            //使用mybatis的第一步：获取sqlSessionFactory的对象
            String resource ="mybatis-config.xml";
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        }catch (IOException e){
            e.printStackTrace();
        }}

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
