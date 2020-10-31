package servlets.admin;

import model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AdminService;
import servlets.user.ReaderServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChangeAuthorServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderServlet.class);
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = null;
        try {
            authors = adminService.getAllAuthors();
        } catch (SQLException | ClassNotFoundException ex) {
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/view/admin/change_author.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String change = req.getParameter("btn_change");
        String delete = req.getParameter("btn_delete");
        int authorId = Integer.parseInt(req.getParameter("author_id"));
        if (delete != null) {
            try {
                adminService.deleteAuthorById(authorId);
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/change_author");
        } else if (change != null) {
            Author author = new Author(authorId, req.getParameter("firstname"), req.getParameter("lastname"),
                    req.getParameter("patronymic"));
            try {
                adminService.changeAuthor(author);
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/change_author");

        } else {
            resp.sendRedirect(req.getContextPath() + "/my_admin");
        }
    }
}
