package com.example.ecommerce.security.plugin;

import com.example.ecommerce.security.JwtManager;
import com.example.ecommerce.service.serviceImpl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: LoginFilter
 * @Author 杨金鹏
 * @Package com.example.ecommerce.security.plugin
 * @Date 2024/1/30 18:01
 */

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 当登录时，会从前端获取一个token，请求头中获取token字符串并解析
        Claims claims = jwtManager.parse(request.getHeader("token"));
        if(claims!=null){
            // 从`JWT`中提取出之前存储好的用户名
            String username = claims.getSubject();
            //查询用户对象
            UserDetails user = userService.loadUserByUsername(username);
            // 手动组装一个认证对象
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            // 将认证对象放到上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
