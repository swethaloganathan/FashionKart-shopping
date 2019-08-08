package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.CartService;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class CartServiceInitializer {

    private static CartService cartService;
    private static CartServiceInitializer cartServiceInitializer;
    private static Logger logger = Logger.getLogger(CartServiceInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private CartServiceInitializer() {
        cartService = new CartService();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static CartService getInstance() {
        if (null == cartService) {
            logger.info("cartService singleton:" + cartService);
            cartServiceInitializer = new CartServiceInitializer();
            return cartService;
        }
        return cartService;
    }
}
