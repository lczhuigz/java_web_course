<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>jsp:include动作</title>
</head>
<body>
<%
    pageContext.setAttribute("name", "张三",
            PageContext.PAGE_SCOPE);
%>
通过include指令引入的页面内容<br>
<%@include file="includeContext.jsp"%>
<br>
通过jsp:include动作引入的页面内容<br>
<jsp:include page="includeContext.jsp"/>
</body>
</html>