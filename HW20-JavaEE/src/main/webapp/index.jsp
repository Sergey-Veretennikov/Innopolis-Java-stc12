<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<body>
<%
    if ("logout".equals(request.getParameter("action"))) request.getSession().invalidate();
%>
<H3>Главная страница</H3>
<div>
    <form>
        <fieldset>
            <legend>Войти в систему</legend>
            <a href="/login">Войти</a><BR><BR>
        </fieldset>
        <br/>
        <fieldset>
            <legend>Зарегистрироваться</legend>
            <a href="/registration">Регистрация</a><BR><BR>
        </fieldset>

    </form>
</div>
</body>
</html>
