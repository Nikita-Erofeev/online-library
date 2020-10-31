package servlets.admin;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AdminService;
import servlets.loginServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(loginServlet.class);
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.info("Login {}", login);
        LOGGER.info("Password {}", password);
        HttpSession session = req.getSession();
        try {
            User user =  adminService.getAdminByLoginPass(login, password);
            if (user == null) {
                LOGGER.info("User == null");
                resp.sendRedirect("/site2/login");
            } else {
                LOGGER.info("User != null");
                session.setAttribute("adminID", user.getId());
                session.setAttribute("adminLogin", user.getLogin());

                /*Куки для логина*/
//                Cookie cookieID = new Cookie("userID", String.valueOf(user.getId()));
//                Cookie cookieLogin = new Cookie("userLogin", user.getLogin());
//                cookieID.setMaxAge(60 * 60);
//                cookieLogin.setMaxAge(60 * 60);
//                resp.addCookie(cookieID);
//                resp.addCookie(cookieLogin);

                LOGGER.debug("SEND REDIRECT /my_admin");
                resp.sendRedirect(req.getContextPath() + "/my_admin");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
