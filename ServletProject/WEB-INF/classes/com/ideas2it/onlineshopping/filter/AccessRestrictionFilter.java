package com.ideas2it.onlineshopping.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.onlineshopping.service.UserService;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * Class which is invoked each time a path is requested and that path is checked
 * for access to continue further
 * </p>
 */
public class AccessRestrictionFilter implements Filter{
    
    private List<String> excludedUrls;
    private static Logger logger = Logger.getLogger(AccessRestrictionFilter.class);

    /**
     * <p>
     * To intialize the filter configuration
     * </p>
     *
     * @param filterconfig - it is used to get the initialization parameter
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Access restriction filter initialized");
        String excludePattern = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePattern.split(","));
    }

    /**
     * <p>
     * Method which is invoked for each URL request, it checks the condition and
     * allow access to each user
     * </p>
     *
     * @param servletRequest - indicates the request of the servlet
     * @param servletResponse - indiacates the response of the servlet
     * @param chain - represents the chaining action of the filter
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String path = request.getServletPath();
System.out.println(path);
            HttpSession session = request.getSession();
            if (!(path.equals("/index.jsp"))) {
                if (null == session.getAttribute("role")) {
                    throw new ShoppingException("Kindly login again to access this page!!!");
                }
                String roleName = (String) session.getAttribute("role");
                if (roleName == "ROLE_USER" && excludedUrls.contains(path)) {
                    throw new ShoppingException("Permission denied for this control");
                }
                chain.doFilter(servletRequest, servletResponse);
            } else {
                if (null == session.getAttribute("role")) {
                    chain.doFilter(servletRequest, servletResponse);
                } else {
                    throw new ShoppingException("Session already exist!!! please logout to login again");
                }
            }
        } catch (ShoppingException exception) {
            logger.error("Exception occurred in access restriction filter", exception);
            servletRequest.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("error.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }
    
    /**
     * <p>
     * To destroy the filter configuration
     * </p>
     */
    public void destroy() {
        logger.info("Access restriction filter destroyed");
    }
}
