package filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String loginURL = "/site2/login";
        String adminURL = "/site2/admin";

        Set<String> adminUrls = new HashSet<>();
        adminUrls.add("/site2/my_admin");
        adminUrls.add("/site2/add_author");
        adminUrls.add("/site2/add_book");
        adminUrls.add("/site2/change_author");
        adminUrls.add("/site2/change_book");
        adminUrls.add("/site2/report");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean loggedAsAdmin = session != null && session.getAttribute("adminID") != null;
        boolean requestToAdmin = adminUrls.contains(request.getRequestURI());

        boolean loggedIn = session != null && session.getAttribute("userID") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURL) || request.getRequestURI().equals(adminURL);

        if((loggedAsAdmin & requestToAdmin) || loginRequest){
            filterChain.doFilter(request, response);
        } else if ((loggedIn & !requestToAdmin) || loginRequest) {
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
