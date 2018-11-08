<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<html>
<body>
<H3>Регистрация пользователя в системе:</H3>
<%
    if (("notAllFieldsFilled").equals(request.getParameter("action"))) {
%>
<div style="color:#AA0000">Не все поля заполнены!</div>
<%
    }
%>
<%
    if (("studentExists").equals(request.getParameter("action"))) {
%>
<div style="color:#9caa13">Такой пользователь уже зарегистрирован в системе!</div>
<%
    }
%>
<form method="post" action="/registration">
    <fieldset>
        <legend>Данные о пользователе</legend>
        Login: <br/><input type="text" name="login"/><br/>
        Password:<br/> <input type="password" name="password"/><br/>
        <br>
        <input type="radio" name="role" value="Student"/> Студент<br/>
        <input type="radio" name="role" value="Teacher"/> Преподаватель<br/>
        <br>
        <input type="submit">
    </fieldset>
</form>
<form action="/index.jsp">
    <button type="submit">Назад</button>
</form>
</body>
</html>
