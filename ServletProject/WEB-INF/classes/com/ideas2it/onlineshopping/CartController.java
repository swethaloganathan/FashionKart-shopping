package com.ideas2it.onlineshopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.model.Cart;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.service.CartService;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.CartServiceInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * In this class, number of products added in cart can be viewed, updated or
 * removed. It allows access only to cart module.
 * </p>
 */
public class CartController extends HttpServlet {

    private User user;
    private CartService cartService = CartServiceInitializer.getInstance();
    private static Logger logger = Logger.getLogger(CartController.class);

    /**
     * <p>
     * The Servlet doPost method Called by the server to allow a servlet to
     * handle a POST request which is used for adding product
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/create-cart":
                saveCartForm(request, response);
                break;
            case "/save-cart":
                saveCart(request, response);
                break;
        }
    }

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
            case "/display-cart":
                display(request, response);
                break;
            case "/edit-cart":
                updateForm(request, response);
                break;
            case "/update-cart":
                update(request, response);
                break;
            case "/delete-cart":
                delete(request, response);
                break;
        }
    }

    /**
     * <p>
     * Method which is used to show the form, in which user enters the user
     * informations
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void saveCartForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            cartService.getProductById(productId);
            request.setAttribute("productId", productId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createCart.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Problem while showing save form of cart", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
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
    private void saveCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setQuantity(quantity);
            cartService.create(cart, productId);
            response.sendRedirect("display-cart");
        } catch (ShoppingException exception) {
            logger.error("Problem while saving the product in cart", exception);
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
            List<Cart> cartDetails = cartService.getCartByUserId(user.getId());
            request.setAttribute("cartDetails", cartDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayCart.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while displaying cart", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Method used to display the updation form based on the user id
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cartId = Integer.parseInt(request.getParameter("id"));
            Cart cart = cartService.getCart(cartId);
            request.setAttribute("cart", cart);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateCart.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while getting a particular cart", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Update method is used to get the edited input of the each user based on
     * user id and update it in the database
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int cartId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String date = request.getParameter("createdDate");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setQuantity(quantity);
            logger.info("Cart Id: " + cart.getId());
            cartService.updateDetail(cart, productId, cartId, date);
            response.sendRedirect("display-cart");
        } catch (ShoppingException exception) {
            logger.error("Error while updating cart", exception);
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
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cartId = Integer.parseInt(request.getParameter("id"));
            cartService.delete(cartId);
            response.sendRedirect("display-cart");            
        } catch (ShoppingException exception) {
            logger.error("Error while deleting cart", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
