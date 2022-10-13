package com.example.demo.service;


import com.example.demo.mapper.NoteMapper;
import com.example.demo.model.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoteService {

    @Resource
    private NoteMapper noteMapper;
    //查询全部的笔记
    public List<Note> selectAll(){
        return noteMapper.selectAll();
    }

    //根据博客Id获取到指定的博客内容（用于在博客详情页）
    public Note selectId(int noteId){
        return noteMapper.selectId(noteId);
    }
    //从博客表中删除指定博客（博客Id）
    public void delete(int noteId){
        noteMapper.delete(noteId);
    }
    //向笔记表里，插入一条笔记
    public void insert(Note note){
        noteMapper.insert(note);
    }
}
