<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
</head>
<body>
登录
<form method="post" action="/login">
    <input type="text" name="username">
    <input type="text" name="password">
    <img>
    <input type="text" name="valid_code">
    <input type="submit" value="提交">
</form>
<%--    <shiro:principal></shiro:principal>--%>
</body>
</html>