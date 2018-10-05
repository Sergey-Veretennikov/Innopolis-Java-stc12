
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
        Возраст: <br/><input type="number" min="10" max="40" name="age"/><br/>
        Контакт: <br/><input type="text" name="contact"/><br/><br/>
        <input type="submit">
    </fieldset>
</form>
<BR>
<form action="/">
    <button type="submit">Возврат на главную страницу</button>
</form>
</body>
</html>
