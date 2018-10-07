<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<body>
<div>
    <h3>Вы вошли как <%=request.getSession().getAttribute("login")%>,
        <a href="/index.jsp?action=logout">выйти</a></h3>
</div>
<div>
    <%
        if (request.getSession().getAttribute("login") != null) {
    %>
    <fieldset>
        <a href="/inner/students">Список всех студентов</a><br><br>
        <a href="/inner/addStudents">Добавить студента в базу</a><br>
    </fieldset>
    <%
        }
    %>
    <br>
    <form action="/index.jsp">
        <button type="submit">Назад</button>
    </form>
</div>
</body>
</html>
