package taskManager.servlet;

import taskManager.manager.TaskManager;
import taskManager.manager.UserManager;
import taskManager.model.User;
import taskManager.typeStatus.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();
    private final TaskManager taskManager = new TaskManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.getByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            String page = "";
            if (user.getUserType().equals(UserType.USER)) {
                req.setAttribute("userName", user.getName());
                req.setAttribute("userId", user.getId());
                req.getRequestDispatcher("/user.jsp").forward(req, resp);
            } else if (user.getUserType().equals(UserType.MANAGER)) {
                req.getRequestDispatcher("/manager.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("wrongData", "Wrong Email Or Password");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}