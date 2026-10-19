<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>脚本片段</title>
</head>
<body>
<%--编写Java代码，计算0~9的累加和--%>
<%
    int sum=0;
    for(int i = 0; i < 10; i++){
        sum+=i;
    }
    out.println("sum: " + sum); // 将结果输出到客户端
%>
</body>
</html>