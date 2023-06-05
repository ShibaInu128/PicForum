<!-- 在页面中使用JSTL标签库 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>帖子列表</title>
</head>
<body>
<!-- 在页面中使用EL表达式 -->
<%@ page isELIgnored="false" %>
<!-- 在页面中显示标题 -->
<h1>帖子列表</h1>
<!-- 在页面中显示分类选择框 -->
<form action="post" method="get">
    <input type="hidden" name="action" value="list">
    <select name="type" onchange="this.form.submit()">
        <option value="all" ${type == 'all' ? 'selected' : ''}>全部</option>
        <option value="show" ${type == 'show' ? 'selected' : ''}>晒图</option>
        <option value="apo" ${type == 'apo' ? 'selected' : ''}>邀约</option>
    </select>
</form>
<!-- 在页面中遍历postList -->
<c:forEach var="post" items="${postList}">
    <!-- 在页面中显示每个帖子的信息 -->
    <div class="post">
        <h3 class="title"><a href="post?action=detail&pid=${post.pid}">${post.title}</a></h3>
        <p class="content">${post.content}</p>
        <p class="type">${post.type}</p>
        <p class="postName">${post.postName}</p>
        <p class="timeUpdate">${post.timeUpdate}</p>
    </div>
</c:forEach>
<!-- 在页面中显示分页导航栏 -->
<div class="pagination">
    <!-- 如果当前页不是第一页，显示上一页链接 -->
    <c:if test="${page > 1}">
        <a href="post?action=list&page=${page - 1}&size=${size}&type=${type}">上一页</a>
    </c:if>
    <!-- 显示当前页码和总页数 -->
    <span>${page}/${dao.getTotalPage(size, type)}</span>
    <!-- 如果当前页不是最后一页，显示下一页链接 -->
    <c:if test="${page < dao.getTotalPage(size, type)}">
        <a href="post?action=list&page=${page + 1}&size=${size}&type=${type}">下一页</a>
    </c:if>
</div>
</body>
</html>
