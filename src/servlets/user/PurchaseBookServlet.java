package servlets.user;

import model.Book;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class PurchaseBookServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        if (bookId == 0) {
            resp.sendRedirect(req.getContextPath() + "/library");
        } else {
            try {
                Book book = userService.getBookById(bookId);
                req.setAttribute("book", book);
            } catch (SQLException | ClassNotFoundException ex) {
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("/view/user/purchase.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("book_id"));
        int userId = (int) req.getSession().getAttribute("userID");
        String bookName = req.getParameter("book_name");
        String author = req.getParameter("author");
        String price = req.getParameter("price");
        String login = (String) req.getSession().getAttribute("userLogin");
        try {
            if(userService.buyBook(userId, bookId, login)){
                resp.setContentType("application/msword");
                resp.setHeader("Content-Disposition", "attachment; filename=cheque.doc");
                OutputStream out = resp.getOutputStream();
                String cheque = "Чек \n" +
                        "Пользователь: " + login + "\n" +
                        "Название книги: " + bookName + "\n" +
                        "Автор книги: " + author + "\n" +
                        "Стоимость: " + price + "\n" +
                        "Статус: Оплачено";
                out.write(cheque.getBytes());
                out.flush();
                out.close();
            }
        } catch (ClassNotFoundException | SQLException ex){
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
