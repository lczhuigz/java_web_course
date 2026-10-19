<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<html>
<head>
</head>
<body>
<c:set var="score" value="85"/>
您的成绩等级为：
<c:choose>
    <c:when test="${score>90}">优秀</c:when>
    <c:when test="${score<=90 and score>80}">良好</c:when>
    <c:when test="${score<=80 and score>70}">合格</c:when>
    <c:otherwise>不合格</c:otherwise>
</c:choose>
</body>
</html>