<%@ page import="com.example.picforum.model.PostInfo" %>
<%@ page import="com.example.picforum.model.PostInfoReply" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子详情</title>
</head>
<body>
<h1>帖子详情</h1>
<%-- 获取request中的帖子数据和回复数据 --%>
<% PostInfo post = (PostInfo) request.getAttribute("post"); %>
<% List<PostInfoReply> replyList = (List<PostInfoReply>) request.getAttribute("replyList"); %>

<%-- 显示帖子标题，内容，类型，发帖用户名和更新时间 --%>
<p>标题：<%= post.getTitle() %>
</p>
<p>内容：<%= post.getContent() %>
</p>
<p>类型：<%= post.getType() %>
</p>
<p>发帖人：<%= post.getPostName() %>
</p>
<p>更新时间：<%= post.getTimeUpdate() %>
</p>

<%-- 显示回复列表 --%>
<h2>回复列表</h2>
<%-- 判断回复列表是否为空 --%>
<% if (replyList == null || replyList.isEmpty()) { %>
<p>暂无回复</p>
<% } else { %>
<%-- 遍历回复列表，显示每条回复的内容，回复用户名和更新时间 --%>
<% for (PostInfoReply reply : replyList) { %>
<div style="border: 1px solid black; margin: 10px; padding: 10px;">
    <p>回复内容：<%= reply.getContent() %>
    </p>
    <p>回复人：<%= reply.getPostName() %>
    </p>
    <p>更新时间：<%= reply.getTimeUpdate() %>
    </p>
</div>
<% } %>
<% } %>

<%-- 显示添加回复的表单，提交到PostInfoServlet的reply方法 --%>
<h2>添加回复</h2>
<form action="PostInfoServlet?method=reply" method="post">
    <%-- 隐藏域，传递帖子id --%>
    <input type="hidden" name="pid" value="<%= post.getPid() %>">
    回复内容：<textarea name="content" rows="5" cols="50"></textarea><br/>
    回复人：<input type="text" name="postName"><br/>
    <input type="submit" value="提交">
</form>

</body>
</html>