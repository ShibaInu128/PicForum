package com.example.picforum.model;

import java.sql.Timestamp;

public class User {
    // 用户id
    private int uid;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 注册时间
    private Timestamp timeRegister;
    // 最后登录时间
    private Timestamp timeLastLogin;

    // 构造方法
    public User() {
    }

    // 带参构造方法
    public User(int uid, String username, String password, Timestamp timeRegister, Timestamp timeLastLogin) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.timeRegister = timeRegister;
        this.timeLastLogin = timeLastLogin;
    }

    // getter和setter方法
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTimeRegister() {
        return timeRegister;
    }

    public void setTimeRegister(Timestamp timeRegister) {
        this.timeRegister = timeRegister;
    }

    public Timestamp getTimeLastLogin() {
        return timeLastLogin;
    }

    public void setTimeLastLogin(Timestamp timeLastLogin) {
        this.timeLastLogin = timeLastLogin;
    }
}