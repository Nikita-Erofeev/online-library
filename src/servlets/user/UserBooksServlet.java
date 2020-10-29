package servlets.user;

import model.Book;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserBooksServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userID = (int) req.getSession().getAttribute("userID");
        try {
            List<Book> books = userService.getBooksByUserID(userID);
            req.setAttribute("books", books);
            req.getRequestDispatcher("/view/user/user_books.jsp").forward(req,resp);
        } catch (ClassNotFoundException | SQLException ex){
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
