package servlets;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class loginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(loginServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.info("Login {}", userName);
        LOGGER.info("Password {}", password);

        HttpSession session = req.getSession();
        try {
            User user = userService.getUserByLoginPass(userName, password);
            if (user == null) {
                LOGGER.info("User == null");
                resp.sendRedirect("/site2/login");
            } else {
                LOGGER.info("User != null");
                session.setAttribute("userID", user.getId());
                session.setAttribute("userLogin", user.getLogin());
                req.setAttribute("user", user);
                req.getRequestDispatcher("/view/user/user.jsp").forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
//        super.doPost(req, resp);
    }
}
