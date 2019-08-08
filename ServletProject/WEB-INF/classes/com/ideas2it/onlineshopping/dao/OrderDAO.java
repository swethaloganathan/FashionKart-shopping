package com.ideas2it.onlineshopping.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ideas2it.onlineshopping.database.Connector;
import com.ideas2it.onlineshopping.model.Order;
import com.ideas2it.onlineshopping.model.OrderDetail;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * This class used to store, fetch, update and delete order details
 * </p>
 */
public class OrderDAO {

    private static Logger logger = Logger.getLogger(OrderDAO.class);

    /**
     * <p>
     * Method in which the products are added to order
     * </p>
     *
     * @param orderDetails - list of products in order
     * @param orderDetail - contains each individual product information
     * @return - it returns the list of product details in order
     */
    public Set<OrderDetail> createOrder(Set<OrderDetail> orderDetails, OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        return orderDetails;
    }

    /**
     * <p>
     * It is used to place order for each user based on their user id
     * </p>
     *
     * @param order - contains all the order details
     */
    public void addOrder(Order order) throws ShoppingException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Connector.getSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Problem while placing an order... order Id: " + order.getId(), exception);
            throw new ShoppingException("Error occurred while placing an order");
        }
    }

    /**
     * <p>
     * To display all the orders
     * </p>
     *
     * @return - it returns all the order information
     */
    public List<Order> displayOrder() throws ShoppingException {
        List<Order> orders = new ArrayList<Order>();
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            orders = session.createQuery("FROM Order").list();
            transaction.commit();
            return orders;
        } catch (HibernateException exception) {
            logger.error("Problem while getting all the order history", exception);
            throw new ShoppingException("Error while displaying Order information");
        }
    }

    /**
     * <p>
     * To get particular order detail based on id
     * </p>
     *
     * @param orderId - it indicates the particular order id entered by user
     * @return - it returns the particular order information
     */
    public Order getOrderById(int orderId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Order order = (Order) session.get(Order.class, orderId);
            transaction.commit();
            return order;
        } catch (HibernateException exception) {
            logger.error("Problem while getting an order detail.. order id" + orderId, exception);
            throw new ShoppingException("Error while getting a particular order information");
        }
    }

    /**
     * <p>
     * To delete a particular order based on order id
     * </p>
     *
     * @param orderId - it indicates the particular order id entered by user 
     */
    public void deleteOrder(int orderId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Order order = (Order) session.get(Order.class, orderId);
            order.setIsActive(Boolean.FALSE);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Problem while deleting an order detail.. order id" + orderId, exception);
            throw new ShoppingException("Problem while cancelling order");
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
    public List<Order> getOrderByUserId (int userId) throws ShoppingException {
        Session session = null;
        try {
            session = Connector.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Order WHERE USER_ID =:userId");
            query.setParameter("userId", userId);
            List orders = query.list();
            transaction.commit();
            return orders;
        } catch (HibernateException exception) {
            logger.error("Error while getting orders based on user id... user Id: " + userId, exception);
            throw new ShoppingException("Error while displaying order information");
        }
    }
}
