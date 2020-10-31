package servlets.admin;

import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class ReportServlet extends HttpServlet {
    private AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> report = adminService.getReport();
            resp.setContentType("application/msword");
            resp.setHeader("Content-Disposition", "attachment; filename=cheque.doc");
            OutputStream out = resp.getOutputStream();
            String cheque = "Отчет \n" +
                    "Книг куплено : "+ report.get(0) + " на сумму: "
                    + report.get(1) + "\n" +
                    "Подробный отчет:\n" +
                    "Название книги           Сумма \n"  +
                    report.get(2);
            out.write(cheque.getBytes());
            out.flush();
            out.close();

        } catch (ClassNotFoundException | SQLException ex) {
            req.setAttribute("error", ex);
            req.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(req, resp);
        }
    }
}
