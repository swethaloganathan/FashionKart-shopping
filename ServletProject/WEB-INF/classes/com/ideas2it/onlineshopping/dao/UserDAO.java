package com.ideas2it.onlineshopping.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.database.Connector;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * In this class, each user informations are stored in the list which can be 
 * retrieved for updation and deletion operation 
 * </p>
 */
public class UserDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    /**
     * <p>
     * Method which is used to create user
     * </p>
     *
     * @param user - it contains individual user information
     */
    public void create(User user) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred at database while adding a user... User Id: " +
                         user.getId() + "User Name: " + user.getUsername(), exception);
            throw new ShoppingException("Error occurred while creating a user");
        }
    }

    /**
     * <p>
     * To display all the user informations
     * </p>
     *
     * @return - it returns the all user informations
     */
    public List<User> displayUser() throws ShoppingException {
        List<User> users = new ArrayList<User>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            return users;
        } catch (HibernateException exception) {
            logger.error("Problem occurred at database while displaying the users", exception);
            throw new ShoppingException("Error while displaying user information");
        }
    }

    /**
     * <p>
     * To update a particular user based on user id
     * </p>
     *
     * @param user - contains the user information to be updated
     */
    public void updateUser(User user) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Error occurred at database while updating the User Id: " + user.getId(), exception);
            throw new ShoppingException("Error occurred while updating a user");
        }
    }

    /**
     * <p>
     * To delete a particular user based on user id
     * </p>
     *
     * @param userId - it indicates the particular user id entered by user 
     */
    public void deleteUser(int userId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            user.setIsActive(Boolean.FALSE);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred at database while deleting the User Id: " + userId, exception);
            throw new ShoppingException("Problem occurred while deleting a user");
        }
    }

    /**
     * <p>
     * To get particular user detail based on id
     * </p>
     *
     * @param userId - it indicates the particular user id entered by user
     * @return - it returns the particular user information
     */
    public User getUserById(int userId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            transaction.commit();
            return user;
        } catch (HibernateException exception) {
            logger.error("Problem occurred at database while getting the information of User Id: " + userId, exception);
            throw new ShoppingException("Error while loading an user information");
        }
    }

    /**
     * <p>
     * To get particular user detail based on id
     * </p>
     *
     * @param userId - it indicates the particular user id entered by user
     * @return - it returns the particular user information
     */
    public List<User> getUserByUsername(String username) throws ShoppingException {
        List<User> userDetails = new ArrayList<User>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where username = :username");
            query.setParameter("username", username);
            userDetails = query.list();
            transaction.commit();
            return userDetails;
        } catch (HibernateException exception) {
            logger.error("Problem occurred at database while getting the information of user: " + username, exception);
            throw new ShoppingException("Error while loading an user information");
        }
    }

    /**
     * <p>
     * Method in which all the roles for each user are stored
     * </p>
     *
     * @param role - contains each role information
     * @param roleSet - the group of roles are added for each user
     * @return - it returns the group of roles after adding each role detail
     */
    public List<Role> add(Role role, List<Role> roles) {
        roles.add(role);
        return roles;
    }
}
