package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.service.ProductService;

/**
 * <p>
 * It is a singleton class in which single instance is created at a time
 * </p>
 */
public class ProductServiceInitializer {

    private static ProductService productService;
    private static ProductServiceInitializer productServiceInitializer;
    private static Logger logger = Logger.getLogger(ProductServiceInitializer.class);

    /**
     * <p>
     * It is default constructor in which each time instance is invoked
     * </p>
     */
    private ProductServiceInitializer() {
        productService = new ProductService();
    }

    /**
     * <p>
     * It calls the default constructor if there is no connection
     * </p>
     *
     * @return - it returns an instance at a time
     */
    public static ProductService getInstance() {
        if (null == productService) {
            logger.info("productService singleton:" + productService);
            productServiceInitializer = new ProductServiceInitializer();
            return productService;
        }
        return productService;
    }
}
