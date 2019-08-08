package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.OrderService;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class OrderServiceInitializer {

    private static OrderService orderService;
    private static OrderServiceInitializer orderServiceInitializer;
    private static Logger logger = Logger.getLogger(OrderServiceInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private OrderServiceInitializer() {
        orderService = new OrderService();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static OrderService getInstance() {
        if (null == orderService) {
            logger.info("orderService singleton:" + orderService);
            orderServiceInitializer = new OrderServiceInitializer();
            return orderService;
        }
        return orderService;
    }
}
