<%@ page import="ru.innopolis.hw20.pojo.Student" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../header.jsp" %>
<html>
<body>
<H3>Список студентов:</H3>
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
<form action="/inner/dashboard">
    <button type="submit">Назад</button>
</form>
</body>
</html>