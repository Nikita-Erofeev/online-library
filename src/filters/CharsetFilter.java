package filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;
    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
