package taskManager.servlet;

import taskManager.manager.UserManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String strId = req.getParameter("id");
        long id = Long.parseLong(strId);
        userManager.delete(id);
        resp.sendRedirect("allUsers.jsp");
    }
}