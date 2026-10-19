<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<html>
<head>
  <title>表达式标签</title>
</head>
<body>
<%
  List<String> list= new ArrayList<>();
  list.add("张三");
  list.add("李四");
  list.add("王五");
  request.setAttribute("list",list);
%>
基于索引范围迭代list：
<c:forEach begin="0" end="${list.size() - 1}" var="index">
  ${list[index]} &nbsp;
</c:forEach>
<br>
基于集合迭代list：
<c:forEach items="${list}" var="item">
  ${item} &nbsp;
</c:forEach>
</body>
</html>