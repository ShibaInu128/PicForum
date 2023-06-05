<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.picforum.model.User" %>
<%@ page import="com.example.picforum.model.PostInfo" %>
<%@ page import="com.example.picforum.model.PostInfoReply" %>
<%@ page import="com.example.picforum.dao.UserDao" %>
<%@ page import="com.example.picforum.dao.PostInfoDao" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>我的信息</title>
</head>
<body>
<h1>我的信息</h1>
<%-- 获取session中的用户对象 --%>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%-- 判断用户是否登录 --%>
<% if (user == null) { %>
<%-- 未登录，跳转到登录页面 --%>
<script>alert("请先登录");
location.href = "login.jsp";</script>
<% } else { %>
<%-- 已登录，显示用户信息 --%>
<p>用户名：<%= user.getUsername() %>
</p>
<p>密码：<%= user.getPassword() %>
</p>
<p>注册时间：<%= user.getTimeRegister() %>
</p>
<p>最后登录时间：<%= user.getTimeLastLogin() %>
</p>

<%-- 显示修改用户信息的表单，提交到UserServlet的update方法 --%>
<h2>修改用户信息</h2>
<form action="user?action=update" method="post">
    <%-- 隐藏域，传递用户id --%>
    <input type="hidden" name="uid" value="<%= user.getUid() %>">
    用户名：<input type="text" name="username" value="<%= user.getUsername() %>"><br/>
    密码：<input type="password" name="password" value="<%= user.getPassword() %>"><br/>
    <input type="submit" value="保存">
</form>

<%-- 创建数据库操作对象 --%>
<% PostInfoDao postInfoDao = new PostInfoDao(); %>

<%-- 显示用户发布的帖子列表 --%>
<h2>我的帖子</h2>
<%-- 调用数据库操作类，查询用户发布的帖子数据 --%>
<% List<PostInfo> postList = postInfoDao.getPostListByPostUid(user.getUid()); %>

<%-- 判断帖子列表是否为空 --%>
<% if (postList == null || postList.isEmpty()) { %>
<p>暂无帖子</p>
<% } else { %>
<%-- 遍历帖子列表，显示每个帖子的标题，内容，类型，更新时间和删除链接 --%>
<% for (PostInfo post : postList) { %>
<div style="border: 1px solid black; margin: 10px; padding: 10px;">
    <p>标题：<%= post.getTitle() %>
    </p>
    <p>内容：<%= post.getContent() %>
    </p>
    <p>类型：<%= post.getType() %>
    </p>
    <p>更新时间：<%= post.getTimeUpdate() %>
    </p>

    <%-- 显示删除链接，跳转到PostInfoServlet的delete方法，并传递帖子id参数 --%>
    <a href="PostInfoServlet?method=delete&pid=<%= post.getPid() %>">删除</a>

</div>

<% } %>

<% } %>

<%-- 显示用户发布的回复列表 --%>
<h2>我的回复</h2>

<%-- 调用数据库操作类，查询用户发布的回复数据 --%>
<% List<PostInfoReply> replyList = postInfoDao.getPostReplyByPostUid(user.getUid()); %>

<%-- 判断回复列表是否为空 --%>
<% if (replyList == null || replyList.isEmpty()) { %>

<p>暂无回复</p>

<% } else { %>

<%-- 遍历回复列表，显示每条回复的内容，更新时间和删除链接 --%>
<% for (PostInfoReply reply : replyList) { %>

<div style="border: 1px solid black; margin: 10px; padding: 10px;">
    <p>回复内容：<%= reply.getContent() %>
    </p>
    <p>更新时间：<%= reply.getTimeUpdate() %>
    </p>

    <%-- 显示删除链接，跳转到PostInfoServlet的deleteReply方法，并传递回复id参数 --%>
    <a href="PostInfoServlet?method=deleteReply&rid=<%= reply.getRid() %>">删除</a>

</div>

<% } %>

<% } %>

<% } %>
</body>
</html>