package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.RoleDAO;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class RoleDAOInitializer {

    private static RoleDAO roleDAO;
    private static RoleDAOInitializer roleDAOInitializer;
    private static Logger logger = Logger.getLogger(RoleDAOInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private RoleDAOInitializer() {
        roleDAO = new RoleDAO();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static RoleDAO getInstance() {
        if (null == roleDAO) {
            logger.info("RoleDAO singleton:" + roleDAO);
            roleDAOInitializer = new RoleDAOInitializer();
            return roleDAO;
        }
        return roleDAO;
    }
}
