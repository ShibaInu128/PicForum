package com.example.picforum.model;

import java.sql.Timestamp;

public class PostInfo {
    private int pid; // 帖子id
    private String title; // 帖子标题
    private String content; // 帖子内容
    private String type; // 帖子类型，show或apo
    private int postUid; // 发帖用户id
    private String postName; // 发帖用户名
    private Timestamp timeUpdate; // 帖子更新时间

    // 无参构造方法
    public PostInfo() {
    }

    // 有参构造方法
    public PostInfo(int pid, String title, String content, String type, int postUid, String postName) {
        this.pid = pid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.postUid = postUid;
        this.postName = postName;
        this.timeUpdate = new Timestamp (System.currentTimeMillis ());
    }
    public PostInfo(int pid, String title, String content, String type, int postUid, String postName, Timestamp timeUpdate) {
        this.pid = pid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.postUid = postUid;
        this.postName = postName;
        this.timeUpdate = timeUpdate;
    }

    // getter和setter方法
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPostUid() {
        return postUid;
    }

    public void setPostUid(int postUid) {
        this.postUid = postUid;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}