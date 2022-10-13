package com.example.demo.aop;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到session对象
        HttpSession session=request.getSession(false);
        if (session!=null && session.getAttribute("user")!=null){
            return true;
        }
        //未登录就跳转到登录页
        response.sendRedirect("/blog_login.html");
        return false;
    }


}
