<%@ page import="ru.innopolis.hw20.pojo.Student" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список студентов:</title>
</head>
<body>
<H3>Список студентов</H3>
<%
    List<Student> list = (List<Student>) request.getAttribute("list");
    for (Student student : list) {
%>
<fieldset>
    <legend>Данные о студенте</legend>
    Имя:  <%=student.getSurname()%> <br/>
    Фамилия: <%=student.getName()%> <br/>
    Возраст: <%=student.getAge()%> <br/>
    Контакт: <%=student.getContact()%> <br/>
    Группа №: <%=student.getGroup().getName()%><br/>
</fieldset>
<%
    }
%>
<BR>
<form action="/">
    <button type="submit">Возврат на главную страницу</button>
</form>
</body>
</html>