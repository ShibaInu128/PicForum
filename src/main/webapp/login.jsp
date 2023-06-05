<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h1>用户登录</h1>
<%-- 显示登录表单，提交到UserServlet的login方法 --%>
<form action="user?action=login" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    <input type="submit" value="登录">
</form>
<%-- 显示注册链接，跳转到register.jsp页面 --%>
<p>还没有账号？<a href="register.jsp">点击注册</a></p>
</body>
</html>