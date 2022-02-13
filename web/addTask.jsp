<%@ page import="taskManager.manager.UserManager" %>
<%@ page import="taskManager.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 13.02.2022
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Manager</title>
</head>
<body>
<div style="margin: auto; width: 600px; text-align: center">
    <form action="/addTask" method="post">
        <label for="desc">Task Description:</label><br>
        <input type="text" id="desc" name="desc"><br>
        <label for="deadline">Deadline</label><br>
        <input type="date" id="deadline" name="deadline"><br>
        <label for="user">User</label><br>
        <select name="user" id="user">
            <%
                List<User> users = new UserManager().getAllUsers();
                for (User user : users) {
            %>
            <option value="<%=user.getId()%>"><%=user.getName() + " " + user.getSurname()%></option>
            <%}%>
        </select><br>
        <input type="submit" value="add task">
    </form>


</div>

</body>
</html>
