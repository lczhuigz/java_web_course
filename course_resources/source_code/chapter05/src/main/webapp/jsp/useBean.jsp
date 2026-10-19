<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp:useBean动作</title>
</head>
<body>
<%--创建User类的实例对象并设置属性值--%>
<jsp:useBean id="user" class="com.itheima.User" scope="request"/>
<jsp:setProperty name="user" property="name" value="李四"/>
<jsp:setProperty name="user" property="age" value="18"/>
<%--获取并输出属性值--%>
姓名：<jsp:getProperty name="user" property="name" /> <br>
年龄：<jsp:getProperty name="user" property="age"/>
</body>
</html>