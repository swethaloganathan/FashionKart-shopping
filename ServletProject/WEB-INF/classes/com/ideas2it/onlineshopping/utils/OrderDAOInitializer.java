package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.OrderDAO;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class OrderDAOInitializer {

    private static OrderDAO orderDAO;
    private static OrderDAOInitializer orderDAOInitializer;
    private static Logger logger = Logger.getLogger(OrderDAOInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private OrderDAOInitializer() {
        orderDAO = new OrderDAO();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static OrderDAO getInstance() {
        if (null == orderDAO) {
            logger.info("orderDAO singleton:" + orderDAO);
            orderDAOInitializer = new OrderDAOInitializer();
            return orderDAO;
        }
        return orderDAO;
    }
}
