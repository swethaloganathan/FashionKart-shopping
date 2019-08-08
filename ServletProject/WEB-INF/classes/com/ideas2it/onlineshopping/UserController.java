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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.UserDAO;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.service.UserService;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.UserServiceInitializer;
import com.ideas2it.onlineshopping.utils.PasswordEncryption;
import com.ideas2it.onlineshopping.exception.ShoppingException;
import com.ideas2it.onlineshopping.utils.Validation;

/**
 * <p>
 * In this class, user informations can be created, displayed, updated and
 * deleted using menu method. Menu method provides access to only user module.
 * </p>
 */
public class UserController extends HttpServlet {

    private Role role;
    private UserService userService = UserServiceInitializer.getInstance();
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * <p>
     * The Servlet doPost method Called by the server to allow a servlet to
     * handle a POST request which is used for adding user
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/create-user":
                saveUserForm(request, response);
                break;
            case "/save-user":
                saveUser(request, response);
                break;
        }
    }

    /**
     * <p>
     * doGet method in which each function is performed based on the requested url
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/display-user":
                display(request, response);
                break;
            case "/edit-user":
                updateForm(request, response);
                break;
            case "/update-user":
                update(request, response);
                break;
            case "/delete-user":
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
    private void saveUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Role> roles = userService.getAllRole();
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Problem occurred while creating user" , exception);
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
    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String encrptedPassword = PasswordEncryption.getPassword(password);
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String address=request.getParameter("address");
            String phone=request.getParameter("phoneNumber");
            String[] arrayOfRole = request.getParameterValues("roles");
            User user = new User();
            user.setUsername(username);
            user.setPassword(encrptedPassword);
            user.setName(name);
            ValidateEmail(user, email);
            user.setAddress(address);
            ValidatePhoneNumber(user, phone);
            userService.createUser(user, arrayOfRole);
            response.sendRedirect("display-user");
        } catch (ShoppingException exception) {
            logger.error("Problem occurred while creating user" , exception);
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
            List<User> userDetails = userService.display();
            request.setAttribute("userDetails", userDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayUser.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while displaying user", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Method used to validate phone number
     * </p>
     *
     * @param user - it contains the each user information
     */
    private void ValidatePhoneNumber(User user, String phone) {
        if (Validation.validatePhoneNumber(phone)) {
            user.setPhone(phone);
        } else {
            logger.info("Invalid phone number");
        }
    }

    /**
     * <p>
     * Method used to validate email id
     * </p>
     *
     * @param user - it contains the each user information
     */
    private void ValidateEmail(User user, String email) {
        if (Validation.validateEmail(email)) {
            user.setEmail(email);
        } else {
            logger.info("Invalid email Id");
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
            int userId = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUser(userId);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while getting a particular user detail", exception);
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
            User user = new User();
            int userId = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String encrptedPassword = PasswordEncryption.getPassword(password);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String date = request.getParameter("createdDate");
            HttpSession session = request.getSession();
            List<Role> roleDetails = (List<Role>) session.getAttribute("roles");
            user.setRoles(roleDetails);
            user.setUsername(username);
            user.setPassword(encrptedPassword);
            user.setName(name);
            ValidateEmail(user, email);
            user.setAddress(address);
            ValidatePhoneNumber(user, phone);
            user.setIsActive(status);
            logger.info("User Id: " + user.getId() + "User Name: " + user.getUsername());
            userService.updateUserDetail(user, userId, date);
            response.sendRedirect("display-user");
        } catch (ShoppingException exception) {
            logger.error("Error while updating user", exception);
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
            int userId = Integer.parseInt(request.getParameter("id"));
            userService.delete(userId);
            response.sendRedirect("display-user");
        } catch (ShoppingException exception) {
            logger.error("Error while deleting user", exception);
            
        }
    }
}
