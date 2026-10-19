<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
<head>
    <title>JSP内置对象pageContext</title>
</head>
<body>
<%
    // 在 page 作用域中设置属性
    pageContext.setAttribute("att1", "page_value",
            PageContext.PAGE_SCOPE);
    // 在 request 作用域中设置属性
    pageContext.setAttribute("att2", "request_value",
            PageContext.REQUEST_SCOPE);
    // 在 application 作用域中设置属性
    pageContext.setAttribute("att1","app_value",
            PageContext.APPLICATION_SCOPE);
    pageContext.setAttribute("att2", "app_value",
            PageContext.APPLICATION_SCOPE);
%>
<%--获取属性值--%>
att1的属性值：<%= pageContext.findAttribute("att1")%> <br>
att2的属性值：<%=pageContext.findAttribute("att2")%>
</body>
</html>