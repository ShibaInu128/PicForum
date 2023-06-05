// PostInfoReply类，封装帖子回复信息
package com.example.picforum.model;

import java.sql.Timestamp;

public class PostInfoReply {
    private int rid; // 回复id
    private int pid; // 帖子id
    private String content; // 回复内容
    private int postUid; // 回复用户id
    private String postName; // 回复用户名
    private Timestamp timeUpdate; // 回复更新时间

    // 无参构造方法
    public PostInfoReply() {
    }

    public PostInfoReply(int rid, int pid, String content, int postUid, String postName) {
        this.rid = rid;
        this.pid = pid;
        this.content = content;
        this.postUid = postUid;
        this.postName = postName;
        this.timeUpdate = new Timestamp (System.currentTimeMillis ());
    }
    // 有参构造方法
    public PostInfoReply(int rid, int pid, String content, int postUid, String postName, Timestamp timeUpdate) {
        this.rid = rid;
        this.pid = pid;
        this.content = content;
        this.postUid = postUid;
        this.postName = postName;
        this.timeUpdate = timeUpdate;
    }

    // getter和setter方法
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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