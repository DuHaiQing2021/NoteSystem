package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/login")
    public Result login(String username, String password, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result=new Result();

        //1.对前端传递的值判断是否为空
        if (username == null||"".equals(username)|| password==null||"".equals(password)){
            result.msg="当前的用户名或者密码为空";
            return result;
        }

        //2.和数据库的内容比较
        User user=userService.login(username);

        if (user==null || !user.getPassword().equals(password)){
            result.msg="用户名或者密码错误";
            return result;
        }

        //3.如果通过，创建会话
        HttpSession session = req.getSession(true);
        //把刚才的用户信息，存储到会话中
        session.setAttribute("user",user);
        //4.返回一个重定向，跳转到博客列表页

        result.msg="登录成功";
        result.succ=200;
//        resp.sendRedirect("/blog_list.html");
        return result;
    }

    @ResponseBody
    @RequestMapping("/prove")
    public User prove(HttpServletRequest req){

        HttpSession session=req.getSession(false);
        if (session==null){
            //检测会话是否存在，不存在说明未登录
            User user=new User();
            return user;
        }

        User user=(User) session.getAttribute("user");
        if (user==null){
            //虽然有会话，但是会话里没有user对象，也是为未登录
            user=new User();
            return user;
        }
        //已经登录的状态
        user.setPassword("");//密码不要传给前端
        return user;
    }

    @ResponseBody
    @RequestMapping("/getauthor")
    public User getAuthor(Integer noteId){
        if (noteId == null || "".equals(noteId)){
            //参数缺少
            return null;
        }
        //根据当前blogId在数据库中进行查找，找到对应的blog对象，再进一步根据blog对象的查看作者id
        Note note=noteService.selectId(noteId);
        if (note == null){
            return null;
        }
        //根据blog对象，查询到用户对象
        User author=userService.selectById(note.getUserId());
        if (author == null){
            return null;
        }
        //注意要把密码去掉
        author.setPassword("");
        return author;

    }

    //注销用户
    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //先找到当前用户的会话
        HttpSession session= req.getSession(false);
        if (session ==null){
            //用户没有登录！！谈不上注销！
            return "当前用户没有登录！无法注销";
        }
        //然后把这个用户的会话中的信息给删除掉
        session.removeAttribute("user");
        resp.sendRedirect("/blog_login.html");
        return null;
    }

}
