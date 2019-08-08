package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.UserService;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class UserServiceInitializer {

    private static UserService userService;
    private static UserServiceInitializer userServiceInitializer;
    private static Logger logger = Logger.getLogger(UserServiceInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private UserServiceInitializer() {
        userService = new UserService();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static UserService getInstance() {
        if (null == userService) {
            logger.info("userService singleton:" + userService);
            userServiceInitializer = new UserServiceInitializer();
            return userService;
        }
        return userService;
    }
}
