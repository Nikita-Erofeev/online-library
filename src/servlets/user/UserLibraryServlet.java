package servlets.user;

import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AdminService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserLibraryServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLibraryServlet.class);
    private UserService userService = new UserService();
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if(search != null && !search.equals("")){
            try {
                int userId = (int) req.getSession().getAttribute("userID");
                List<Book> result = userService.getBookBySearch(userId, search);
                req.setAttribute("books", result);
                req.getRequestDispatcher("/view/user/user_library.jsp").forward(req,resp);
            } catch (ClassNotFoundException | SQLException e){
                req.setAttribute("error", e);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
        } else {
            try {
                List<Book> books = adminService.getAllBooks();
                req.setAttribute("books", books);
            } catch (ClassNotFoundException | SQLException e){
                req.setAttribute("error", e);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("/view/user/user_library.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
