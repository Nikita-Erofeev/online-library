package servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

public class ReaderServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookId = req.getParameter("id") == null ? null: Integer.parseInt(req.getParameter("id")) ;
        int userId = (int) req.getSession().getAttribute("userID") ;
        if(bookId != null && bookId > 0){
            try {
                String path = userService.readBook(userId, bookId);
                if(path == null || path.equals("")){
                    resp.sendRedirect(req.getContextPath() + "/my_book");
                } else {
                    LOGGER.debug("PATH^ {}", path);
                    InputStream is = getServletContext().getResourceAsStream(path);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    String ls = System.getProperty("line.separator");
                    while( ( line = br.readLine() ) != null ) {
                        stringBuilder.append( line );
                        stringBuilder.append( "\n" );
                    }
                    br.close();
                    is.close();
                    req.setAttribute("text", stringBuilder.toString());
                    req.getRequestDispatcher("view/user/reader.jsp").forward(req,resp);
                }
            } catch (ClassNotFoundException | SQLException ex){
                req.setAttribute("error", ex);
                req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/my_book");
        }
    }
}
