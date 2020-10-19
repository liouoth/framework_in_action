<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
</head>
<body>
系统主页（受限资源）需要登录
<ul>
    <shiro:hasAnyRoles name="user,admin">
        <li><a>用户管理</a>
            <ul>
                <shiro:hasPermission name="user:add:people">
                    <li><a href="/people/add">增加</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:people">
                    <li>删</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:query:people">
                    <li>查</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:people">
                    <li><a href="/people/update">更改</a></li>
                </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
    <%--  需要admin角色才能看得到     --%>
    <shiro:hasRole name="admin">
        <li><a>茶叶蛋管理</a></li>
        <li><a>资源管理</a></li>
    </shiro:hasRole>
</ul>
<form action="/logout">
    <input type="submit" value="退出">
</form>
</body>
</html>