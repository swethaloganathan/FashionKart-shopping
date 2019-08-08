package com.ideas2it.onlineshopping.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.database.Connector;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * This class stores the list of product details which can be called for further
 * updations.
 * </p>
 */
public class ProductDAO {

    private static Logger logger = Logger.getLogger(ProductDAO.class);

    /**
     * <p>
     * Method used to create each product
     * </p>
     *
     * @param product - contains each individual product information
     */
    public void create(Product product) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while creating product... Product Id: " 
                         + product.getId() + "product Name: " + product.getProductName(), exception);
            throw new ShoppingException("Error occurred while creating a product");
        }
    }

    /**
     * <p>
     * To display all the product informations
     * </p>
     *
     * @return - returns all the product informations which are added 
     */
    public List<Product> displayProduct() throws ShoppingException {
        List<Product> products = new ArrayList<Product>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            products = session.createQuery("FROM Product").list();
            transaction.commit();
            return products;
        } catch (HibernateException exception) {
            logger.error("Error in database while getting all the products", exception);
            throw new ShoppingException("Error while displaying user information");
        }
    }

    /**
     * <p>
     * To update a particular product based on product id
     * </p>
     *
     * @param product - contains the product information to be updated
     */
    public void updateProduct(Product product) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while updating product... Product Id: " + product.getId(), exception);
            throw new ShoppingException("Problem while updating a product detail");
        }
    }

    /**
     * <p>
     * To delete a particular product based on product id
     * </p>
     *
     * @param productId - indicates the product id choosen by user
     */
    public void deleteProduct(int productId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Product product = (Product) session.get(Product.class, productId);
            product.setIsActive(Boolean.FALSE);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem while deleting a product... Product Id: " + productId, exception);
            throw new ShoppingException("Problem while deleting product");
        }
    }

    /**
     * <p>
     * To get particular product based on id
     * </p>
     *
     * @param productId - contains the product id entered by user
     * @return - returns the particular product detail
     */
    public Product getProductById(int productId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Product product = (Product) session.get(Product.class, productId);
            transaction.commit();
            return product;
        } catch (HibernateException exception) {
            logger.error("Problem while getting a product... Product Id: " + productId, exception);
            throw new ShoppingException("Error while getting a particular product information");
        }
    }
}
