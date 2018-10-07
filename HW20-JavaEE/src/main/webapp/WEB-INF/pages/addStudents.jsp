<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../header.jsp" %>
<body>
<H3>Внесение данных стедента в базу:</H3>
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
<div style="color:#9caa13">Студент уже есть в базе!</div>
<%
    }
%>
<form method="post" action="/inner/addStudents">
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
<form action="/inner/dashboard">
    <button type="submit">Назад</button>
</form>
</body>
</html>
