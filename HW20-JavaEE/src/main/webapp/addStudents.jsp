<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.10.2018
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Внесение данных</title>
</head>
<body>
<%
    if (("notAllFieldsFilled").equals(request.getParameter("action"))) {
%>
<div style="color:#AA0000">Не все поля заполнены!</div>
<%}%>
<form method="post" action="/addStudents">
    <fieldset>
        <legend>Данные о студенте</legend>
        Имя: <br/><input type="text" name="name"/><br/>
        Фамилия:<br/> <input type="text" name="surname"/><br/>
        № группы: <br/><input type="text" name="nameGroup"/><br/>
        Возраст: <br/><input type="text" name="age"/><br/>
        Контакт: <br/><input type="text" name="contact"/><br/><br/>
        <input type="submit">
    </fieldset>
</form>
</body>
</html>
