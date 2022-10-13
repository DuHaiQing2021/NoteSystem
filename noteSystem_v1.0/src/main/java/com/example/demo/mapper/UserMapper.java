package com.example.demo.mapper;


import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //需要实现的操作
    //1.根据用户名来查找用户信息
    //  会在登录逻辑中使用
    public User selectByName(@Param("username") String username);

    //2.根据用户Id来查找用户信息
    //  博客详情页，就可以根据用户Id来查询作者的名字，把作者名字显示出来。
    public User selectById(@Param("userId") int userId);



}
