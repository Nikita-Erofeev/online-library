package servlets.admin;

import dao.AdminDao;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddAuthorServlet extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/admin/add_author.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String patronymic = req.getParameter("patronymic");
        if(firstname !=null & lastname != null & patronymic != null){
            try {
                if(adminService.addAuthor(firstname, lastname, patronymic)){
                    resp.sendRedirect(req.getContextPath() + "/add_author");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/add_author");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/add_author");
        }
    }
}
