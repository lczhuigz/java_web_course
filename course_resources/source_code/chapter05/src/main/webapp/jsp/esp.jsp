<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<html>
<head>
    <title>表达式标签</title>
</head>
<body>
<c:set var="number" scope="page" value="${20*5}"/>
移除number前的值：<c:out value="${pageScope.number}"/> <br>
<c:remove var="number" scope="page"/>
移除number后的值：<c:out value="${pageScope.number}"/>
</body>
</html>