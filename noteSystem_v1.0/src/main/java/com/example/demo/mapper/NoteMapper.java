package com.example.demo.mapper;


import com.example.demo.model.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoteMapper {
    //1.向笔记表里，插入一条笔记
    public void insert(Note note);
    //2.能够获取到博客表里的所有博客的信息（用于在博客列表页）
    public List<Note> selectAll();
    //3.根据博客Id获取到指定的博客内容（用于在博客详情页）
    public Note selectId(@Param("noteId") int noteId);
    //4.从博客表中删除指定博客（博客Id）
    public void delete(@Param("noteId") int noteId);

}