package taskManager.servlet;

import taskManager.manager.TaskManager;
import taskManager.manager.UserManager;
import taskManager.model.Task;
import taskManager.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {
    private final TaskManager taskManager = new TaskManager();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String desc = req.getParameter("desc");
        String strDate = req.getParameter("deadline");
        long id = Long.parseLong(req.getParameter("user"));
        User user = userManager.getById(id);


        Date deadline = null;
        try {
            deadline = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Task task = new Task();
        task.setDescription(desc);
        task.setDeadline(deadline);
        task.setUser(user);

        taskManager.addTask(task);

        resp.sendRedirect("manager.jsp");

    }
}
