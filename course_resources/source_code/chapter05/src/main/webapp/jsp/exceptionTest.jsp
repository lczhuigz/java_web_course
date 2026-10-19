<%@ page contentType="text/html;charset=GBK"
         language="java" isErrorPage="true" %>
<html>
<head>
    <title>错误处理页面</title>
</head>
<body>
异常信息：<%= exception.getMessage() %> <br>
异常类型：<%= exception.getClass().getName()%>
</body>
</html>