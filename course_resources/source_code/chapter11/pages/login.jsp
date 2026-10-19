<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录页面</title>
    </head>
    <body>
        <h2>用户登录</h2>
        <form action="${pageContext.request.contextPath}/user/login" method="post">
            <label for="name">用户名:</label>
            <input type="text" id="name" name="name" required><br>
            <label for="password">密&nbsp;&nbsp;&nbsp;码:</label>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="登录">
        </form>
        <c:if test="${not empty loginError}">
            <p style="color: red;">${loginError}</p>
        </c:if>
    </body>
</html>
