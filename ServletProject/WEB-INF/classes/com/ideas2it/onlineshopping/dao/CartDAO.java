package com.ideas2it.onlineshopping.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.database.Connector;
import com.ideas2it.onlineshopping.model.Cart;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * This class stores the list of product details in cart
 * </p>
 */
public class CartDAO {

    private static Logger logger = Logger.getLogger(CartDAO.class);

    /**
     * <p>
     * Method in which the products are added to cart
     * </p>
     *
     * @param cart - contains each individual cart information
     */
    public void createCart(Cart cart) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem occurred in database while adding product in cart... cart Id: " + cart.getId(), exception);
            throw new ShoppingException("Error occurred while adding product to cart");
        }
    }

    /**
     * <p>
     * To display all the cart informations
     * </p>
     *
     * @return - it returns all the cart information
     */
    public List<Cart> displayCart() throws ShoppingException {
        List<Cart> carts = new ArrayList<Cart>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            carts = session.createQuery("FROM Cart").list();
            transaction.commit();
            return carts;
        } catch (HibernateException exception) {
            logger.error("Error displaying product in cart", exception);
            throw new ShoppingException("Error while displaying cart information");
        }
    }

    /**
     * <p>
     * To update a particular cart detail based on cart id
     * </p>
     *
     * @param cart - conatins the particular cart detail to be updated
     */
    public void updateCart(Cart cart) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(cart);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem in database while updating product in cart... cart Id: " + cart.getId(), exception);
            throw new ShoppingException("Problem while updating a cart detail");
        }
    }

    /**
     * <p>
     * To delete a particular cart based on cart id
     * </p>
     *
     * @param cartId - it indicates the cart id choosen by the user
     */
    public void deleteCart(int cartId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, cartId);
            session.delete(cart);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Error while deleting product in cart... cart Id: " + cartId, exception);
            throw new ShoppingException("Problem while deleting cart");
        }
    }

    /**
     * <p>
     * To get particular cart detail based on its id
     * </p>
     *
     * @param cartId - it indicates the cart id choosen by the user
     * @return - it returns the particular cart information based on id
     */
    public Cart getCartById(int cartId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Cart cart = (Cart) session.get(Cart.class, cartId);
            transaction.commit();
            return cart;
        } catch (HibernateException exception) {
            logger.error("Error while getting a cart... cart Id: " + cartId, exception);
            throw new ShoppingException("Error while getting a particular cart information");
        }
    }

    /**
     * <p>
     * To get particular cart detail for a user based on user id
     * </p>
     *
     * @param userId - it indicates the user id entered by the user
     * @return - it returns the cart information of particular user based on id
     */
    public List<Cart> getCartByUserId (int userId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Cart WHERE USER_ID =:userId");
            query.setParameter("userId", userId);
            List carts = query.list();
            transaction.commit();
            return carts;
        } catch (HibernateException exception) {
            logger.error("Error while getting a cart based on user id... user Id: " + userId, exception);
            throw new ShoppingException("Error while displaying cart information");
        }
    }
}
