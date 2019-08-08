package com.ideas2it.onlineshopping.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.UserService;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.utils.PasswordEncryption;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * Filter class is used to check login of each user using username and password.
 * It gets the login request and check for the user details, if correct user
 * detail exists, it passes the response to next resource
 * </p>
 */
public class LoginFilter implements Filter{

    private static Logger logger = Logger.getLogger(LoginFilter.class);
  
    /**
     * <p>
     * To intialize the filter configuration
     * </p>
     *
     * @param config - it is used to get the initialization parameter
     */
    public void init(FilterConfig config) throws ServletException {
        logger.info("Filter initialized");
    }
    
    /**
     * <p>
     * Method which is used to redirect the request, response and chain to
     * secured filter method
     * </p>
     *
     * @param servletRequest - indicates the request of the servlet
     * @param servletResponse - indiacates the response of the servlet
     * @param chain - represents the chaining action of the filter
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String encrptedPassword = PasswordEncryption.getPassword(password);
            Boolean isRoleAdmin = Boolean.FALSE;
            UserService userService = new UserService();
            User user = userService.getUsername(username);
            if (null == user) {
                throw new ShoppingException("Username does not exist !!!");
            }
            if (!(encrptedPassword.equals(user.getPassword()))) {
                throw new ShoppingException("Incorrect password !!!");
            }
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user",user);
                    session.setAttribute("role", "ROLE_ADMIN");
                    chain.doFilter(request, response);
                    isRoleAdmin = Boolean.TRUE;
                }
            }
            if (!isRoleAdmin) {
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                session.setAttribute("role", "ROLE_USER");
                chain.doFilter(request, response);
            }
        } catch (ShoppingException exception) {
            logger.error("Exception occurred at login filter", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }       
    }

    /**
     * <p>
     * To destroy the filter configuration
     * </p>
     */
    public void destroy() {
        logger.info("Filter destroyed");
    }
}
