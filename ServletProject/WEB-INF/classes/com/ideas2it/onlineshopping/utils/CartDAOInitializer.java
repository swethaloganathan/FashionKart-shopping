package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.CartDAO;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class CartDAOInitializer {

    private static CartDAO cartDAO;
    private static CartDAOInitializer cartDAOInitializer;
    private static Logger logger = Logger.getLogger(CartDAOInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private CartDAOInitializer() {
        cartDAO = new CartDAO();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static CartDAO getInstance() {
        if (null == cartDAO) {
            logger.info("CartDAO singleton:" + cartDAO);
            cartDAOInitializer = new CartDAOInitializer();
            return cartDAO;
        }
        return cartDAO;
    }
}
