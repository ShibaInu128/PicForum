package com.example.picforum.dao;

import com.example.picforum.model.PostInfo;
import com.example.picforum.model.PostInfoReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostInfoDao extends DAO {

    public List<PostInfo> getPostListByPageAndType(int page, int size, String type) {
        List<PostInfo> postList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = null;
            if (type.equals("all")) {  // 如果类型为all，则查询所有帖子
                sql = "SELECT * FROM pic_post_info LIMIT ?, ?";
            } else {  // 否则根据类型查询帖子
                sql = "SELECT * FROM pic_post_info WHERE type = ? LIMIT ?, ?";
            }
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            if (type.equals("all")) {  // 如果类型为all，则设置参数为起始记录数和每页记录数
                ps.setInt(1, (page - 1) * size);
                ps.setInt(2, size);
            } else {  // 否则设置参数为类型、起始记录数和每页记录数
                ps.setString(1, type);
                ps.setInt(2, (page - 1) * size);
                ps.setInt(3, size);
            }
            // 执行查询
            rs = ps.executeQuery();
            // 遍历结果集，封装PostInfo对象，添加到列表中
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setPid(rs.getInt("pid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setType(rs.getString("type"));
                post.setPostUid(rs.getInt("post_uid"));
                post.setPostName(rs.getString("post_name"));
                post.setTimeUpdate(rs.getTimestamp("time_update"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return postList;
    }

    // 添加新回复的方法
    public boolean addNewReply(PostInfoReply reply) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "INSERT INTO pic_post_info_reply (pid, content, post_uid, post_name) VALUES (?, ?, ?, ?)";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子id，第二个参数是回复内容，第三个参数是回复用户id，第四个参数是回复用户名
            ps.setInt(1, reply.getPid());
            ps.setString(2, reply.getContent());
            ps.setInt(3, reply.getPostUid());
            ps.setString(4, reply.getPostName());
            // 执行更新操作，返回影响的行数
            int rows = ps.executeUpdate();
            // 判断是否更新成功，如果影响的行数大于0，则表示成功，否则表示失败
            if (rows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, null);
        }
        return result;
    }

    // 根据pid删除帖子的方法
    public boolean deletePostByPid(int pid) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "DELETE FROM pic_post_info WHERE pid = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子id
            ps.setInt(1, pid);
            // 执行更新操作，返回影响的行数
            int rows = ps.executeUpdate();
            // 判断是否更新成功，如果影响的行数大于0，则表示成功，否则表示失败
            if (rows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, null);
        }
        return result;
    }
    // 根据页码和每页记录数查询帖子列表数据
    public List<PostInfo> getPostListByPage(int page, int size) {
        List<PostInfo> postList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_post_info LIMIT ?, ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是起始记录数，第二个参数是每页记录数
            ps.setInt(1, (page - 1) * size);
            ps.setInt(2, size);
            // 执行查询
            rs = ps.executeQuery();
            // 遍历结果集，封装PostInfo对象，添加到列表中
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setPid(rs.getInt("pid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setType(rs.getString("type"));
                post.setPostUid(rs.getInt("post_uid"));
                post.setPostName(rs.getString("post_name"));
                post.setTimeUpdate(rs.getTimestamp("time_update"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return postList;
    }

    // 根据pid查询帖子详情数据
    public PostInfo getPostByPid(int pid) {
        PostInfo post = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_post_info WHERE pid = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子id
            ps.setInt(1, pid);
            // 执行查询
            rs = ps.executeQuery();
            // 判断结果集是否有数据，如果有则封装PostInfo对象
            if (rs.next()) {
                post = new PostInfo();
                post.setPid(rs.getInt("pid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setType(rs.getString("type"));
                post.setPostUid(rs.getInt("post_uid"));
                post.setPostName(rs.getString("post_name"));
                post.setTimeUpdate(rs.getTimestamp("time_update"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return post;
    }

    // 根据pid查询帖子被回复的数据
    public List<PostInfoReply> getPostReplyByPid(int pid) {
        List<PostInfoReply> replyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_post_info_reply WHERE pid = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子id
            ps.setInt(1, pid);
            // 执行查询
            rs = ps.executeQuery();
            // 遍历结果集，封装PostInfoReply对象，添加到列表中
            while (rs.next()) {
                PostInfoReply reply = new PostInfoReply();
                reply.setRid(rs.getInt("rid"));
                reply.setPid(rs.getInt("pid"));
                reply.setContent(rs.getString("content"));
                reply.setPostUid(rs.getInt("post_uid"));
                reply.setPostName(rs.getString("post_name"));
                reply.setTimeUpdate(rs.getTimestamp("time_update"));
                replyList.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return replyList;
    }

    // 添加新帖子的方法
    public boolean addNewPost(PostInfo post) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "INSERT INTO pic_post_info (title, content, type, post_uid, post_name) VALUES (?, ?, ?, ?, ?)";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子标题，第二个参数是帖子内容，第三个参数是帖子类型，第四个参数是发帖用户id，第五个参数是发帖用户名
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getType());
            ps.setInt(4, post.getPostUid());
            ps.setString(5, post.getPostName());
            // 执行更新操作，返回影响的行数
            int rows = ps.executeUpdate();
            // 判断是否更新成功，如果影响的行数大于0，则表示成功，否则表示失败
            if (rows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, null);
        }
        return result;
    }

    // 根据type查询帖子列表的方法
    public List<PostInfo> getPostListByType(String type) {
        List<PostInfo> postList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_post_info WHERE type = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数，第一个参数是帖子类型
            ps.setString(1, type);
            // 执行查询操作，返回结果集
            rs = ps.executeQuery();
            // 遍历结果集，封装PostInfo对象，添加到列表中
            while (rs.next()) {
                PostInfo post = new PostInfo();
                post.setPid(rs.getInt("pid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setType(rs.getString("type"));
                post.setPostUid(rs.getInt("post_uid"));
                post.setPostName(rs.getString("post_name"));
                post.setTimeUpdate(rs.getTimestamp("time_update"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return postList;
    }
    public List<PostInfoReply> getPostReplyByPostUid(int postUid) {
        List<PostInfoReply> replyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 定义sql语句
            String sql = "SELECT * FROM pic_post_reply WHERE post_uid = ?";
            // 预编译sql语句
            ps = conn.prepareStatement(sql);
            // 设置参数
            ps.setInt(1, postUid);
            // 执行查询
            rs = ps.executeQuery();
            // 判断结果集是否有数据
            while (rs.next()) {
                // 创建回复对象
                PostInfoReply reply = new PostInfoReply();
                // 封装回复数据
                reply.setRid(rs.getInt("rid"));
                reply.setPid(rs.getInt("pid"));
                reply.setContent(rs.getString("content"));
                reply.setPostUid(rs.getInt("post_uid"));
                reply.setPostName(rs.getString("post_name"));
                reply.setTimeUpdate(rs.getTimestamp("time_update"));
                // 将回复对象添加到列表中
                replyList.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeResource(conn, ps, rs);
        }
        return replyList;
    }
}