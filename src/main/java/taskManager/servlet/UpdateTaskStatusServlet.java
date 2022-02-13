package taskManager.servlet;

import taskManager.manager.TaskManager;
import taskManager.manager.UserManager;
import taskManager.model.User;
import taskManager.typeStatus.TaskStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateTaskStatus")
public class UpdateTaskStatusServlet extends HttpServlet {
    private final TaskManager taskManager = new TaskManager();
    private final UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        TaskStatus status = TaskStatus.valueOf(req.getParameter("status"));
        long id = Long.parseLong(req.getParameter("taskId"));
        long userId = Long.parseLong(req.getParameter("taskUserId"));

        User user = userManager.getById(userId);

        if (taskManager.update(id, status)) {
            req.getRequestDispatcher("/login?email=" + user.getEmail() + "&password=" +
                    user.getPassword() + "").forward(req, resp);
        }
    }
}
