<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>购物车</h1>
<table border="1">
    <tr>
        <th>名称</th>
        <th>单价 (元)</th>
        <th>数量</th>
        <th>小计 (元)</th>
    </tr>
    <c:set var="total" value="0" />
    <c:choose>
        <c:when test="${not empty cartItems}">
            <c:forEach var="entry" items="${cartItems}">
                <c:set var="item" value="${entry.value}" />
                <tr>
                    <td>${item.clothes.name}</td>
                    <td>${item.clothes.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.clothes.price * item.quantity}</td>
                </tr>
                <c:set var="total" value="${total + item.clothes.price * item.quantity}" />
            </c:forEach>
            <tr>
                <td colspan="3">总计 (元)</td>
                <td>${total}</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4">购物车为空</td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<a href="clothes">继续购物</a>
</body>
</html>
