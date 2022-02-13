<%@ page import="taskManager.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="taskManager.manager.TaskManager" %>
<%@ page import="taskManager.typeStatus.TaskStatus" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Hovhanes Gevorgyan
  Date: 12.02.2022
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Manager</title>
    <link rel="stylesheet" href="cssFiles/user.css">
</head>
<body>
<div id="container"><br>

    <div id="header">
        <h2>Welcome <%=request.getAttribute("userName")%> !!!</h2>
    </div>

    <div id="tasks">
        <form action="/updateTaskStatus" method="post">
            <table>
                <tr>
                    <th>Task Description</th>
                    <th>Status</th>
                    <th>Deadline</th>
                </tr>
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    List<Task> tasks = new TaskManager().getByUserId((Long) request.getAttribute("userId"));
                    for (Task task : tasks) {
                %>
                <tr>
                    <td><%=task.getDescription()%>
                    </td>
                    <td><%=task.getTaskStatus()%>
                    </td>
                    <td><%=sdf.format(task.getDeadline())%>
                    </td>
                    <td><label for="status">
                        <select name="status" id="status">
                            <option value="<%=TaskStatus.NEW%>">NEW</option>
                            <option value="<%=TaskStatus.IN_PROCESS%>">IN PROCESS</option>
                            <option value="<%=TaskStatus.FINISHED%>">FINISHED</option>
                        </select>
                        <input type="hidden" name="taskId" value="<%=task.getId()%>">
                        <input type="hidden" name="taskUserId" value="<%=task.getUser().getId()%>">
                        <%}%>
                    </label></td>
                </tr>

            </table>
            <br>
            <input type="submit" value="Update">
        </form>
    </div>


</div>
</body>
</html>