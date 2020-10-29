package servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class logoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(loginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Session invalid, SEND REDIRECT /login");
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
