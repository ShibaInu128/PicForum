<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<%-- 显示注册表单，提交到UserServlet的register方法 --%>
<form action="user?action=register" method="post">
  用户名：<input type="text" name="username"><br/>
  密码：<input type="password" name="password"><br/>
  <input type="submit" value="注册">
</form>
<%-- 显示登录链接，跳转到login.jsp页面 --%>
<p>已有账号？<a href="login.jsp">点击登录</a></p>
</body>
</html>