package com.example.picforum.dao;


import com.example.picforum.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DAO {
    // 根据用户名和密码查询用户
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_user WHERE username = ? AND password = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setString(1, username);
            ps.setString(2, password);
            // 执行查询
            rs = ps.executeQuery();
            // 判断结果集是否有数据
            if (rs.next()) {
                // 创建用户对象
                user = new User();
                // 封装用户数据
                user.setUid(rs.getInt("uid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTimeRegister(rs.getTimestamp("time_register"));
                user.setTimeLastLogin(rs.getTimestamp("time_last_login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return user;
    }

    // 查询用户名是否已存在
    public boolean checkUsernameExists(String username) {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT COUNT(*) FROM pic_user WHERE username = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setString(1, username);
            // 执行查询
            rs = ps.executeQuery();
            // 判断结果集是否有数据
            if (rs.next()) {
                // 获取结果集中的第一列数据，即计数值
                int count = rs.getInt(1);
                // 判断计数值是否大于0，如果是，则表示用户名已存在
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return exists;
    }

    // 插入新用户数据
    public void insertUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "INSERT INTO pic_user (username, password) VALUES (?, ?)";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            // 执行更新
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, null);
        }
    }
}

