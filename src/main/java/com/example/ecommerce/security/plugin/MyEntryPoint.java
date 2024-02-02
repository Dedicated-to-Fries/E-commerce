package com.example.ecommerce.security.plugin;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title: MyEntryPoint
 * @Author 杨金鹏
 * @Package com.example.ecommerce.security.plugin
 * @Date 2024/1/17 19:59
 */

public class MyEntryPoint implements AuthenticationEntryPoint {

    /**
     * AuthenticationEntryPoint 认证异常处理器
     * 账号或密码不正确将抛出异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 直接提示前端认证错误
        out.write("账号或密码错误，登录失败");
        out.flush();
        out.close();
    }
}
