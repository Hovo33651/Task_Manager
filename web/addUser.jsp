<%--
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
    <form action="/addUser" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        <label for="surname">Surname:</label><br>
        <input type="text" id="surname" name="surname"><br>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="userType">User Type</label><br>
        <select id="userType" name="userType">
        <option value="USER">USER</option>
        <option value="MANAGER">MANAGER</option>
    </select><br>
        <input type="submit" value="add user">
    </form>

</div>
</body>
</html>
