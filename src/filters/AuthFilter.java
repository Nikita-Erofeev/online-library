package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String loginURL = "/site2/login";
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("userID") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        if (loggedIn || loginRequest) {
//            LOGGER.debug("BOOL in {} req {}", loggedIn, loginRequest);
            filterChain.doFilter(request, response);
        } else {
            LOGGER.debug("SEND REDIRECT FROM FILTER /site2/login");
            response.sendRedirect(loginURL);
        }
    }

    private boolean cookieLogged(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return false;
        }
        boolean t1 = false, t2 = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userID")) {
                t1 = true;
            }
            if (cookie.getName().equals("userLogin")) {
                t2 = true;
            }
        }
        return t1 & t2;
    }

    private String getValueCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
