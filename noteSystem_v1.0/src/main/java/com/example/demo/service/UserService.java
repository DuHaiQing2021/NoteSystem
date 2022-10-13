package com.example.demo.service;


import com.example.demo.mapper.UserMapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //使用用户名进行验证
    public User login(String username){
        User user= userMapper.selectByName(username);
        return user;
    }

    //根据用户Id来查找用户信息
    public User selectById(Integer userId){
        return userMapper.selectById(userId);
    }


}
