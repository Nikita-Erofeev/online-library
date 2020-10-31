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

public class AddBookServlet extends HttpServlet {
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
        req.getRequestDispatcher("/view/admin/add_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author_id = req.getParameter("author_id");
        String name = req.getParameter("book_name");
        String genre_id = req.getParameter("genre_id");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        String path = "/WEB-INF/lib/META-INF/resources/books/" + req.getParameter("path");
        Book book = new Book();
        book.setAuthor(author_id);
        book.setName(name);
        book.setGenre(genre_id);
        book.setDescription(description);
        book.setPrice(price);
        book.setPath(path);
        try {
            if(adminService.addBook(book)){
                resp.sendRedirect(req.getContextPath() + "/add_book");
            } else {
                req.setAttribute("error", "Неправильно выбран жанр или автор");
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
