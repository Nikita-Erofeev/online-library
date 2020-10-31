package servlets.admin;

import model.Author;
import model.Book;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChangeBookServlet extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = null;
        List<Book> books = null;
        try {
            authors = adminService.getAllAuthors();
            books = adminService.getAllBooks();
        } catch (SQLException | ClassNotFoundException ex) {
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
        req.setAttribute("authors", authors);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/view/admin/change_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String change = req.getParameter("btn_change");
        String delete = req.getParameter("btn_delete");
        int bookId = Integer.parseInt(req.getParameter("book_id"));
        if (delete != null) {
            try {
                adminService.deleteBookById(bookId);
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/change_book");
        } else if (change != null) {
            Book book = new Book(bookId, req.getParameter("author_id"), req.getParameter("book_name"),
                    req.getParameter("genre_id"), req.getParameter("description"),
                    Integer.parseInt(req.getParameter("price")), req.getParameter("path"), true);
            try {
                adminService.changeBook(book);
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/change_book");

        } else {
            resp.sendRedirect(req.getContextPath() + "/my_admin");
        }
    }
}
