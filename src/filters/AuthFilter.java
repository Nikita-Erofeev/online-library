package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String loginURL = "/site2/login";
        boolean loggedIn = session != null && session.getAttribute("userID") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        if (loggedIn || loginRequest){
            filterChain.doFilter(request, response);
        } else{
            response.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {

    }
}
