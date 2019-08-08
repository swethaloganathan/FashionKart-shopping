package com.ideas2it.onlineshopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ideas2it.onlineshopping.dao.RoleDAO;
import com.ideas2it.onlineshopping.dao.UserDAO;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;
import com.ideas2it.onlineshopping.utils.RoleDAOInitializer;
import com.ideas2it.onlineshopping.utils.UserDAOInitializer;

/**
 * <p>
 * In this class, all the logical operations are executed
 * </p>
 */
public class UserService {

    private UserDAO userDAO = UserDAOInitializer.getInstance();
    private RoleDAO roleDAO = RoleDAOInitializer.getInstance();

    /**
     * <p>
     * Method in which each user informations are created
     * </p>
     *
     * @param user - contains each individual user information
     */
    public void createUser(User user, String[] arrayOfRole) throws ShoppingException {
        List<Role> roles = new ArrayList<Role>();
        if (null == arrayOfRole) {
            throw new ShoppingException("Please select a role to continue further");
        }
        for (int index = 0; index < arrayOfRole.length; index++) {
            int roleId = Integer.parseInt(arrayOfRole[index]);
            roles = addRole(roleId, roles);
        }
        user.setRoles(roles);
        Timestamp dateTime = DateUtils.getDateTime();
        user.setDateTime(dateTime);
        user.setUpdatedDateTime(dateTime);
        user.setIsActive(Boolean.TRUE);
        userDAO.create(user);
    }

    /**
     * <p>
     * Method to display all the roles present
     * </p>
     *
     * @return - returns all the role details
     */
    public List<Role> getAllRole() throws ShoppingException {
        return roleDAO.displayRole();
    }

    /**
     * <p>
     * To add all the roles selected by the user 
     * </p>
     *
     * @param role - it contains a particular role information
     * @param roleSet - it is used to store all the roles selected by the user
     */
    public List<Role> addRole(int roleId, List<Role> roles) throws ShoppingException {
        Role role = roleDAO.getRoleById(roleId);
        if (null == role || role.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Invalid Role...Data does not exist");
        }
        userDAO.add(role, roles);
        return roles;
    }

    /**
     * <p>
     * Method used to get all the user details
     * </p>
     *
     * @return - it returns all the user informations
     */
    public List<User> display() throws ShoppingException {
        return userDAO.displayUser();
    }

    /**
     * <p>
     * Method used to get the user exists
     * </p>
     *
     * @param userId - it indicates the user id given by the user
     * @return - it returns user information
     */
    public User getUser(int userId) throws ShoppingException {
        User user = userDAO.getUserById(userId);
        if (null == user || user.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Invalid User id, Data does not exist");
        }
        return user;
    }

     /**
     * <p>
     * Method used to get the user exists
     * </p>
     *
     * @param userId - it indicates the user id given by the user
     * @return - it returns user information
     */
    public User getUsername(String username) throws ShoppingException {
        User user;
        List<User> userDetails = userDAO.getUserByUsername(username);
        if (userDetails.size() > 0) {
            user = userDetails.get(0);
        } else {
            user = null;
        }
        return user;
    }

    /**
     * <p>
     * To update user information
     * </p>
     *
     * @param user - contains the user detail to update
     */
    public void updateUserDetail(User user, int userId, String date) throws ShoppingException {
        getUser(userId);
        Timestamp createdDate = DateUtils.convertDate(date);
        user.setDateTime(createdDate);
        Timestamp dateTime = DateUtils.getDateTime();
        user.setUpdatedDateTime(dateTime);
        user.setId(userId);
        userDAO.updateUser(user);
    }

    /**
     * <p>
     * It is a method which is used to delete a particular user information based
     * on user id
     * </p>
     *
     * @param userId - it indicates the user id given by the user
     */
    public void delete(int userId) throws ShoppingException {
        getUser(userId);
        userDAO.deleteUser(userId);
    }
}
