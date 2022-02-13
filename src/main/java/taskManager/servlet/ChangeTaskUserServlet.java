package taskManager.servlet;

import taskManager.manager.TaskManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/changeUser")
public class ChangeTaskUserServlet extends HttpServlet {
    private final TaskManager taskManager = new TaskManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String taskStrId = req.getParameter("taskId");
        String newStrId = req.getParameter("newId");
        long taskId = Long.parseLong(taskStrId);
        long newId = Long.parseLong(newStrId);

        taskManager.changeUser(taskManager.getById(taskId),newId);
        resp.sendRedirect("/manager.jsp");
    }
}