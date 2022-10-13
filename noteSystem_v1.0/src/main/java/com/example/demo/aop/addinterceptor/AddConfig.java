package com.example.demo.aop.addinterceptor;

import com.example.demo.aop.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AddConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/user/login").
                excludePathPatterns("/**/*.js").
                excludePathPatterns("/**/*.css").
                excludePathPatterns("/**/*.jpg").
                excludePathPatterns("/**/*.png").
                excludePathPatterns("/blog_login.html").
                excludePathPatterns("/user/prove");

    }
}
