package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.entity.UserEntity;
import com.example.ecommerce.entity.param.LoginParam;
import com.example.ecommerce.entity.param.UserParam;
import com.example.ecommerce.entity.vo.UserVO;
import com.example.ecommerce.exception.ApiException;
import com.example.ecommerce.security.JwtManager;
import com.example.ecommerce.security.plugin.UserDetail;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Title: UserService
 * @Author 杨金鹏
 * @Package com.example.ecommerce.service
 * @Date 2023/12/30 20:59
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private JwtManager jwtManager;


    //当用户登录时会生成一个JWT令牌
    @Override
    public UserVO login(LoginParam userParam) {
        SqlSession sqlSession =MybatisUtils.getSqlSession();
        UserDao userDao= sqlSession.getMapper(UserDao.class);
        //根据用户名查找出用户实体
        UserEntity user=userDao.findByName(userParam.getUsername());

        // 若没有查到用户或者密码校验失败则抛出异常
        if(user ==null || !new BCryptPasswordEncoder().matches(userParam.getPassword(), user.getPassword())){
            throw new ApiException("无法登录,认证失败");
        }

        UserVO userVo=new UserVO();
          userVo.setId(user.getId())
                  .setUsername(user.getUsername())
                  // 生成JWT，将用户名数据存入其中
                  .setToken(jwtManager.generate(user.getUsername()));
          return userVo;//返回用户ID和姓名、令牌
    }

    @Override
    public void register(UserParam user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserEntity byName = mapper.findByName(user.getUsername());//根据前端输入的用户名和数据库进行对比
        if(byName !=null){//判断返回的List集合是否为空
           throw new ApiException("用户名已存在，无法注册");//判断用户名是否存在，如果存在则抛出异常.
        }
        UserEntity users=new UserEntity();
        users.setId(user.getId());
        users.setUsername(user.getUsername());
        users.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));//对password进行加密
        users.setSex(user.getSex());
        users.setCity(user.getCity());
        users.setProvince(user.getProvince());
        users.setCreatedAt(user.getCreatedAt());
        users.setOrdersList(user.getOrdersList());
        mapper.register(users);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public UserEntity findByName(String username){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserEntity byName = mapper.findByName(username);
        sqlSession.commit();
        sqlSession.close();
        return byName;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserEntity userEntity = mapper.findByName(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("没有找到该用户");
        }
        sqlSession.commit();
        sqlSession.close();
        return new UserDetail(userEntity,Collections.emptyList());
    }
}

