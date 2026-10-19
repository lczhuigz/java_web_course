<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>服装列表</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>服装列表</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>价格 (元)</th>
            <th>操作</th>
        </tr>
        <c:forEach var="clothes" items="${clothesList}">
            <tr>
                <td>${clothes.id}</td>
                <td>${clothes.name}</td>
                <td>${clothes.price}</td>
                <td>
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="clothesId" value="${clothes.id}">
                        <button type="submit">添加到购物车</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
