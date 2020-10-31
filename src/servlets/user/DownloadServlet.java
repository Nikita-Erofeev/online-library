package servlets.user;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

public class DownloadServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));
        int userId = (int) req.getSession().getAttribute("userID");
        try {
            String path = userService.readBook(userId, bookId);
            if(path == null || path.equals("")){
                resp.sendRedirect(req.getContextPath() + "/my_book");
            } else {
                resp.setContentType("application/msword");
                resp.setHeader("Content-Disposition", "attachment; filename=book.doc");
                OutputStream out = resp.getOutputStream();
                InputStream in = getServletContext().getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while( ( line = br.readLine() ) != null ) {
                    stringBuilder.append( line );
                    stringBuilder.append( "\n" );
                }
                br.close();
                in.close();
                String txt = stringBuilder.toString();
                out.write(txt.getBytes());
                out.flush();
                out.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
