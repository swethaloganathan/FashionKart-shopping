package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.dao.ProductDAO;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class ProductDAOInitializer {

    private static ProductDAO productDAO;
    private static ProductDAOInitializer productDAOInitializer;
    private static Logger logger = Logger.getLogger(ProductDAOInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private ProductDAOInitializer() {
        productDAO = new ProductDAO();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static ProductDAO getInstance() {
        if (null == productDAO) {
            logger.info("ProductDAO singleton:" + productDAO);
            productDAOInitializer = new ProductDAOInitializer();
            return productDAO;
        }
        return productDAO;
    }
}
