<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>jsp:forward动作</title>
</head>
<body>
<jsp:forward page="targetPage.jsp">
    <jsp:param name="username" value="张三" />
    <jsp:param name="password" value="123456" />
</jsp:forward>
</body>
</html>