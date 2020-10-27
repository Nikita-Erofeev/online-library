package servlets;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
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

                /*Куки для логина*/
//                Cookie cookieID = new Cookie("userID", String.valueOf(user.getId()));
//                Cookie cookieLogin = new Cookie("userLogin", user.getLogin());
//                cookieID.setMaxAge(60 * 60);
//                cookieLogin.setMaxAge(60 * 60);
//                resp.addCookie(cookieID);
//                resp.addCookie(cookieLogin);

                LOGGER.debug("SEND REDIRECT /my_books");
                resp.sendRedirect(req.getContextPath() + "/my_books");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
