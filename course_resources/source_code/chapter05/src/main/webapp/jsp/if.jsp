<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<html>
<head>
</head>
<body>
<c:set var="score" scope="page" value="85"/>
<c:if test="${score >= 60}">
    您的成绩为：<c:out value="合格"/>
</c:if>
</body>
</html>