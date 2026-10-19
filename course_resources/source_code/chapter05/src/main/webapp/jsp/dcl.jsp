<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计算两个数值的和</title>
</head>
<body>
<%!
    public int sum(int a,int b){
        return a + b;
    }
%>
3与5的和：<%= sum(3,5)%>
</body>
</html>