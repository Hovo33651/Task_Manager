<%@ page import="taskManager.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="taskManager.manager.TaskManager" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 12.02.2022
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>
    <link rel="stylesheet" href="cssFiles/manager.css">

</head>
<body>
<div id="container"><br>

    <div id="header">
        <h2>Welcome <%=request.getAttribute("userName")%> !!!</h2>
    </div>

    <div id="addUser">
        <button onclick="document.location='addUser.jsp'">Add User</button>
    </div><br>

    <div id="addTask">
        <button onclick="document.location='addTask.jsp'">Add Task</button>
    </div>


    <div id="tasks">
        <table>
            <tr>
                <th>Task Description</th>
                <th>Status</th>
                <th>Deadline</th>
                <th>User</th>
            </tr>
            <%TaskManager taskManager = new TaskManager();
                List<Task> tasks = taskManager.getAllTasks();
                for (Task task : tasks) {%>
            <tr>
                <td><%=task.getDescription()%></td>
                <td><%=task.getTaskStatus()%></td>
                <td><%=task.getDeadline()%></td>
                <td><%=task.getUser().getName()+ " "+ task.getUser().getSurname()%></td>
                <th><a href="${pageContext.request.contextPath}/deleteTask?id=<%=task.getId()%>">Delete Task</a></th>
                <th><a href="allUsers.jsp?id=<%=task.getId()%>">Change Developer</a></th>
            </tr>
            <%}%>
        </table>
    </div>


</div>
</body>
</html>