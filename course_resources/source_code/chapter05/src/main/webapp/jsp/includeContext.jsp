<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>jsp:include动作</title>
</head>
<body>
<%
    Object name = pageContext.getAttribute("name",
            PageContext.PAGE_SCOPE);
%>
name的属性值：<%=name%>
</body>
</html>