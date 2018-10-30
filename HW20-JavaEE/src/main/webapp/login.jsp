<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<html>
<body>
<H3>Войти в систему</H3>
<%
    if ("wrongUser".equals(request.getParameter("action"))) {
%>
<div style="color:#AA0000">Неверное имя пользователя/пароль</div>
<%
    }
%>
<%
    if ("noAuth".equals(request.getParameter("action"))) {
%>
<div style="color:#AA0000">Попытка получить доступ к закрытой части сайта. Сначала надо войти в систему</div>
<%
    }
%>
<form action="/login" method="post">
    Логин :<br>
    <input type="text" name="login"><br>
    Пароль: <br>
    <input type="password" name="password"><BR>
    <br>
    <input type="submit">
</form>
<BR>
<form action="/">
    <button type="submit">Назад</button>
</form>
</body>
</html>
