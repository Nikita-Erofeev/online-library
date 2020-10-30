package servlets.user;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddBookToUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        if (bookId == 0) {
            resp.sendRedirect(req.getContextPath() + "/library");
        } else {
            int userId = (int) req.getSession().getAttribute("userID");
            try {
                userService.checkBookIsAvailable(userId, bookId);
                resp.sendRedirect(req.getContextPath() + "/library");
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
        }
    }
}
