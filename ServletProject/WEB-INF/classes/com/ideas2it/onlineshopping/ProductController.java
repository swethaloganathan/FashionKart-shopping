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

import com.ideas2it.onlineshopping.dao.ProductDAO;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.service.ProductService;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.ProductServiceInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * This class contains all the product informations which can be created,
 * displayed, updated and deleted using product Id. In this class, product menu
 * method gives access only to product module.
 * </p>
 */
public class ProductController extends HttpServlet {

    private User user;
    private ProductService productService = ProductServiceInitializer.getInstance();
    private static Logger logger = Logger.getLogger(ProductController.class);

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
            case "/create-product":
                saveProductForm(request, response);
                break;
            case "/save-product":
                saveProduct(request, response);
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
            case "/display-product":
                display(request, response);
                break;
            case "/edit-product":
                updateForm(request, response);
                break;
            case "/update-product":
                update(request, response);
                break;
            case "/delete-product":
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
    public void saveProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createProduct.jsp");
        dispatcher.forward(request, response);
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
    public void saveProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int sellingPrice = Integer.parseInt(request.getParameter("sellingPrice"));
            int mrp = Integer.parseInt(request.getParameter("mrp"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Product product = new Product();
            product.setUser(user);
            product.setProductName(productName);
            product.setDescription(description);
            product.setPrice(price);
            product.setSellingPrice(sellingPrice);
            product.setMrp(mrp);
            productService.createProduct(product);
            response.sendRedirect("display-product");
        }  catch (ShoppingException exception) {
            logger.error("Problem occurred while saving the product", exception);
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
    public void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> productDetails = productService.display();
            request.setAttribute("productDetails", productDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayProduct.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while displaying product", exception);
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
    public void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productService.getProduct(productId);
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateProduct.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while getting a particular product information", exception);
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
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = new Product();
            int productId = Integer.parseInt(request.getParameter("id"));
            String skuId = request.getParameter("sku");
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int sellingPrice = Integer.parseInt(request.getParameter("sellingPrice"));
            int mrp = Integer.parseInt(request.getParameter("mrp"));
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String date = request.getParameter("createdDate");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            product.setSku(skuId);
            product.setUser(user);
            product.setProductName(productName);
            product.setDescription(description);
            product.setPrice(price);
            product.setSellingPrice(sellingPrice);
            product.setMrp(mrp);
            product.setIsActive(status);
            productService.updateProductDetail(product, productId, date);
            logger.info("Product Id: " + product.getId() + "Product Name: " + product.getProductName());
            response.sendRedirect("display-product");
        } catch (ShoppingException exception) {
            logger.error("Error while updating product", exception);
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
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            productService.delete(productId);
            response.sendRedirect("display-product");
        } catch (ShoppingException exception) {
            logger.error("Error while deleting product", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
