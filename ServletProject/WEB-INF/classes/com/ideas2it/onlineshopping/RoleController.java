package com.ideas2it.onlineshopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.service.RoleService;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.RoleServiceInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * In this class, role for each users are created, displayed, updated and deleted
 * by using servlet methods.
 * </p>
 */
public class RoleController extends HttpServlet {

    private RoleService roleService = RoleServiceInitializer.getInstance();
    private static Logger logger = Logger.getLogger(RoleController.class);

    /**
     * <p>
     * The Servlet doPost method Called by the server to allow a servlet to
     * handle a POST request which is used for saving the roles
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/save-role":
                saveForm(request, response);
                break;
            case "/create-role":
                save(request, response);
                break;
        }
    }

    /**
     * <p>
     * Method used to display the form to save role.
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void saveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createRole.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * <p>
     * save method is used to add a new role by getting the user input
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            logger.info("Role name: " + role.getName());
            roleService.create(role);
            response.sendRedirect("display-role");
        } catch (ShoppingException exception) {
            logger.error("Problem occurred while saving role", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * The servlet doGet method is also a service method which is used use the
     * url and perform appropriate display, update and delete funtions
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/display-role":
                display(request, response);
                break;
            case "/edit-role":
                updateForm(request, response);
                break;
            case "/delete-role":
                delete(request, response);
                break;
            case "/update-role":
                update(request, response);
                break;
        }
    }

    /**
     * <p>
     * Method which is used to display all the roles available
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Role> roleDetails = roleService.display();
            request.setAttribute("roleDetails", roleDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayRole.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while displaying roles", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Method used to display the updation form based on the role id
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int roleId = Integer.parseInt(request.getParameter("id"));
            Role role = roleService.getRole(roleId);
            request.setAttribute("role", role);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateRole.jsp");
            dispatcher.forward(request, response);
        } catch (ShoppingException exception) {
            logger.error("Error while loading update form", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Update method is used to get the edited input of the each role based on
     * role id and update it in the database
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Role role = new Role();
            int roleId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String date = request.getParameter("createdDate");
            role.setName(name);
            role.setDescription(description);
            role.setIsActive(status);
            roleService.updateRoleDetail(role, roleId, date);
            logger.info("Role Id: " + role.getId() + "Role Name: " + role.getName());
            response.sendRedirect("display-role");
        } catch (ShoppingException exception) {
            logger.error("Error while updating roles", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * <p>
     * Delete method is used to delete a particular role based on role Id
     * </p>
     *
     * @param request - it carries the request from the client
     * @param response - it carries the response for the request
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Boolean isCorrect;
            int roleId = Integer.parseInt(request.getParameter("id"));
            roleService.deleteDetail(roleId);
            response.sendRedirect("display-role");
        } catch (ShoppingException exception) {
            logger.error("Error while deleting roles", exception);
            request.setAttribute("error", exception.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
