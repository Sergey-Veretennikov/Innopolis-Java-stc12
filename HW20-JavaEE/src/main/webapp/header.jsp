<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Программа учета успеваемости студентов</title>
</head>
<body>
<div class="header">
    <h2>Программа учета успеваемости студентов</h2>
</div>
<div>
    <%
        if (request.getSession().getAttribute("login") != null) {
    %>
    <h3>Вы вошли как <%=request.getSession().getAttribute("login")%>, <a href="/index.jsp?action=logout">выйти</a></h3>
    <%
        }
    %>
</div>
</body>
</html>
