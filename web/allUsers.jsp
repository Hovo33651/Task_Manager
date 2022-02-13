<%@ page import="taskManager.manager.UserManager" %>
<%@ page import="taskManager.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 13.02.2022
  Time: 02:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="cssFiles/allUsers.css">
</head>
<body>
<div id="container">

    <div id="homePage">
        <button onclick="document.location='manager.jsp'">Home Page</button>
    </div>

    <table>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Delete</th>
            <th>Choose</th>
        </tr>
        <%
            UserManager userManager = new UserManager();
            List<User> users = userManager.getAllUsers();
            for (User user : users) {
        %>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getSurname()%></td>
            <td><%=user.getEmail()%></td>
            <td><a href="/deleteUser?id=<%=user.getId()%>">Delete</a></td>
            <td><a href="/changeUser?newId=<%=user.getId()%>&taskId=<%=request.getParameter("id")%>">Choose</a></td>
        </tr>
        <%}%>
    </table>

</div>
</body>
</html>