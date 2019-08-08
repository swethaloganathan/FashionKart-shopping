package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.UserDAO;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class UserDAOInitializer {

    private static UserDAO userDAO;
    private static UserDAOInitializer userDAOInitializer;
    private static Logger logger = Logger.getLogger(UserDAOInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private UserDAOInitializer() {
        userDAO = new UserDAO();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static UserDAO getInstance() {
        if (null == userDAO) {
            logger.info("UserDAO singleton:" + userDAO);
            userDAOInitializer = new UserDAOInitializer();
            return userDAO;
        }
        return userDAO;
    }
}
