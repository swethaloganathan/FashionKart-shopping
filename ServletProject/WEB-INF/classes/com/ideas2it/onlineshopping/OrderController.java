package com.ideas2it.onlineshopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.model.Cart;
import com.ideas2it.onlineshopping.model.Order;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.OrderDetail;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.service.OrderService;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.OrderServiceInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * In this class, number of products ordered can be viewed, updated or
 * removed using order id. It allows access only to order module.
 * </p>
 */
public class OrderController extends HttpServlet {

    private Cart cart;
    private OrderService orderService = OrderServiceInitializer.getInstance();
    private static Logger logger = Logger.getLogger(OrderController.class);

    /**
     * <p>
     * Method which gets all the request and redirect it to appropriate actions
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/create-order":
                saveOrder(request, response);
                break;
            case "/display-order":
                display(request, response);
                break;
            case "/delete-order":
                cancelOrder(request, response);
                break;
        }
    }

    /**
     * <p>
     * Adding individual user information and assigning one or more roles to
     * a particular user
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Order order = new Order();
            order.setUser(user);
            String[] arrayOfCarts = request.getParameterValues("carts");
            orderService.createOrder(order, arrayOfCarts);
            response.sendRedirect("display-order");
        } catch (ShoppingException exception) {
            logger.error("Problem while placing an order", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Method which is used to display all the users
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Order> orderDetails  = orderService.getOrderByUserId(user.getId());
            request.setAttribute("orderDetails", orderDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayOrder.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while displaying the order details", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Delete method is used to delete a particular user based on user Id
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            orderService.delete(orderId);
            response.sendRedirect("display-order");
        } catch (ShoppingException exception) {
            logger.error("Error while deleting product", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
