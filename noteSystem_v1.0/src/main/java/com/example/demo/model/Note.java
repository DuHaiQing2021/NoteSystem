package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
public class Note {
    private int noteId;
    private String title;
    private String content;
    private int userId;
    private Timestamp postTime;
    private int rcount;
    private int state;

    //把这里的getter方法给修改了，不是返回一个时间戳，而是返回一个String（格式化好的时间）
    public String getPostTime(){
        //使用这个类完成时间戳到日期化转换
        //此处的格式如果记不住，要查一下
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(this.postTime);
    }
    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }
}