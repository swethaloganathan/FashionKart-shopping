package com.ideas2it.onlineshopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * Logout class is used to invalidate the session of a user and returns to the
 * login page
 * </p>
 */
public class Logout extends HttpServlet {

    /**
     * <p>
     * Method used to invalidate session if exist and redirect the user to login
     * page
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
System.out.println("logout:" + session);
System.out.println("logout:" + request.getSession(false));
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.include(request, response);
        out.println("You are successfully logged out!");
    }
}
