<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>当前系统时间</title>
</head>
<body>
<h2>当前系统时间</h2>
<%--使用Java代码获取时间--%>
<%
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:SS");
    String nowTime = formatter.format(now);
%>
<%--显示获取到的时间--%>
当前系统时间为：<%= nowTime %>
</body>
</html>