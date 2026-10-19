<%@ page contentType="text/html;charset=UTF-8"
         language="java" errorPage="exceptionTest.jsp" %>
<html>
<head>
</head>
<body>
<%
    String s = null;
    out.println(s.length());  //此处会发生异常
%>
</body>
</html>