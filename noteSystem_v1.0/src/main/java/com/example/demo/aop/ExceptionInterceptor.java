package com.example.demo.aop;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice  //前端数据异常拦截器
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class) //拦截所有异常
    public HashMap<String,Object> exceptionAdvice(Exception e){
        HashMap<String,Object> result=new HashMap<>();
        result.put("state",-1);
        result.put("data",null);
        result.put("msg",e.getMessage());
        return result;
    }

}
