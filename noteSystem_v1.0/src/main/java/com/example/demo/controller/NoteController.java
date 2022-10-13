package com.example.demo.controller;


import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/getallnotes")
    public List<Note> getAllNotes(Integer noteId){

        if (noteId == null){
            //笔记列表
            List<Note> notes =noteService.selectAll();
            List<Note> newNotes=new ArrayList<>();
            for (Note n:notes){
                //笔记中的内容截取50个字符显示在笔记列表页
                if (n.getContent().length()>50){
                    String str=n.getContent().substring(0,50);
                    n.setContent(str);
                }
                newNotes.add(n);
            }
            return newNotes;
        }else {
            //笔记详情
            List<Note> note=new ArrayList<>();
            note.add(noteService.selectId(noteId));
            return note;
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String noteDelete(Integer noteId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //检查当前用户是否登录
        HttpSession session=req.getSession(false);
        if (session == null){
            return "当前尚未登录，不能删除！";
        }
        User user =(User) session.getAttribute("user");
        if (user == null){
            return "当前尚未登陆，不能删除";
        }

        //2.获取要删除的博客信息
        if (noteId == null ){
            return "当前noteId参数不对";
        }
        //3.获取要删除的博客信息

        Note note=noteService.selectId(noteId);
        if (note == null ){
            return "当前要删除的博客不存在";
        }

        //4.再次校验，当前的用户是否就是博客的作者
        if (user.getUserId() != note.getUserId()){
            //这一点在前端这里其实也处理过~~但是此处该市再校验一次，不是坏事
            return "当前登录用户不是作者，没有权限！";
        }
        //5.确认无误，可以删除
        noteService.delete(noteId);
        //6.重定向博客列表页
        resp.sendRedirect("/blog_list.html");
        return null;
    }

    @ResponseBody
    @RequestMapping("/noteinsert")
    public String noteInsert(String title,String content,HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session= req.getSession(false);
        if (session ==null){
            //当前用户没有登录
            return "当前用户未登录，不能提交note";
        }
        User user=(User) session.getAttribute("user");
        if (user==null){
            return "当前用户未登录，不能提交blog";
        }


        if (title == null||"".equals(title)||content==null||"".equals(content)){
            //直接告诉客户端，请求参数不对
            return "提交博客失败！缺少必要的参数！";
        }
        //构造blog对象
        //此处要给Blog设置的属性，主要是title，content，userId（作者信息）
        Note note=new Note();
        note.setTitle(title);
        note.setContent(content);
        //作者id就是当前提交这个博客的用户的身份信息！
//        blog.setBlogId(user.getUserId());
        note.setUserId(user.getUserId());

        noteService.insert(note);
        resp.sendRedirect("/blog_list.html");
        return null;
    }
}
