package com.ideas2it.onlineshopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlineshopping.dao.ProductDAO;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.Role;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.CommonUtils;
import com.ideas2it.onlineshopping.utils.ProductDAOInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * It contains all the logical operations related to product
 * </p>
 */
public class ProductService {

    private ProductDAO productDAO = ProductDAOInitializer.getInstance();

    /**
     * <p>
     * Method in which each product informations are created
     * </p>
     *
     * @param product - contains each individual product information
     */
    public void createProduct(Product product) throws ShoppingException {
        Timestamp dateTime = DateUtils.getDateTime();
        product.setDateTime(dateTime);
        product.setUpdatedDateTime(dateTime);
        product.setSku(CommonUtils.skuGenerator());
        product.setIsActive(Boolean.TRUE);
        productDAO.create(product);
    }

    /**
     * <p>
     * Method to display all the product informations present
     * </p>
     *
     * @return - returns all the product details
     */
    public List<Product> display() throws ShoppingException {
        return productDAO.displayProduct();
    }

    /**
     * <p>
     * Method used to check if the role exists or not
     * </p>
     *
     * @param roleId - it indicates the role id given by the user
     * @return - it returns role if exist else throws error message
     */
    public Product getProduct(int productId) throws ShoppingException {
        Product product = productDAO.getProductById(productId);
        if (null == product || product.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Product not in stock!!!");
        }
        return product;
    }

    /**
     * <p>
     * To update product information
     * </p>
     *
     * @param product - contains the product detail to update
     */
    public void updateProductDetail(Product product, int productId, String date) throws ShoppingException {
        getProduct(productId);
        product.setId(productId);
        Timestamp createdDate = DateUtils.convertDate(date);
        product.setDateTime(createdDate);
        Timestamp dateTime = DateUtils.getDateTime();
        product.setUpdatedDateTime(dateTime);
        productDAO.updateProduct(product);
    }

    /**
     * <p>
     * In this method, particular product can be identified and removed using 
     * product id
     * </p>
     *  
     * @param productId - contains each product id
     */
    public void delete(int productId) throws ShoppingException {
        getProduct(productId);
        productDAO.deleteProduct(productId);
    }
}
