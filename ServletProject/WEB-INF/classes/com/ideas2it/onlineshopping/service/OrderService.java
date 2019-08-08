package com.ideas2it.onlineshopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.onlineshopping.dao.CartDAO;
import com.ideas2it.onlineshopping.dao.OrderDAO;
import com.ideas2it.onlineshopping.dao.ProductDAO;
import com.ideas2it.onlineshopping.model.Cart;
import com.ideas2it.onlineshopping.model.Order;
import com.ideas2it.onlineshopping.model.OrderDetail;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.exception.ShoppingException;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.ProductDAOInitializer;
import com.ideas2it.onlineshopping.utils.CartDAOInitializer;
import com.ideas2it.onlineshopping.utils.OrderDAOInitializer;

/**
 * <p>
 * It contains all the logical operations related to products Ordered
 * </p>
 */
public class OrderService {

    private OrderDAO orderDAO = OrderDAOInitializer.getInstance();
    private CartDAO cartDAO = CartDAOInitializer.getInstance();
    private ProductDAO productDAO = ProductDAOInitializer.getInstance();

    /**
     * <p>
     * It is used to add a order for a particular user
     * </p>
     *
     * @param order - it contains a particular order information
     */
    public void createOrder(Order order, String[] arrayOfCarts) throws ShoppingException {
        if (null == arrayOfCarts) {
                throw new ShoppingException("Please select a product from cart to continue ordering...");
            }
        Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
        for (int index = 0; index < arrayOfCarts.length; index++) {
            int cartId = Integer.parseInt(arrayOfCarts[index]);
            OrderDetail orderDetail = new OrderDetail();
            getProductDetails(orderDetail, cartId);
            orderDetail.setOrder(order);
            orderDetails = orderDAO.createOrder(orderDetails, orderDetail);
        }
        order.setTotalPrice(getPriceFromProduct(orderDetails));
        order.setOrderDetails(orderDetails);
        order.setIsActive(Boolean.TRUE);
        Timestamp dateTime = DateUtils.getDateTime();
        order.setDateTime(dateTime);
        order.setUpdatedDateTime(dateTime);
        orderDAO.addOrder(order);
    }

    /**
     * <p>
     * Method used to calculate total price of all the products for one order id
     * </p>
     *
     * @param orderDetails - it consists of all the product informations for
     * each order
     * @return - it returns the sum of all the product price
     */
    private int getPriceFromProduct(Set<OrderDetail> orderDetails) throws ShoppingException {
        int sum = 0;
        for (OrderDetail orderDetail : orderDetails) {
            int price = orderDetail.getProduct().getPrice();
            int totalPrice = price * orderDetail.getQuantity();
            sum += totalPrice;
        }
        return sum;
    }

    /**
     * <p>
     * Method used to get product details and compare it and set the product
     * detail to particular order
     * </p>
     *
     * @param orderDetail - product details can be stored
     * @param cartId - consists of the cart id given by the user
     * @param cart - contains the particular cart information
     */
    private void getProductDetails (OrderDetail orderDetail, int cartId) throws ShoppingException {
        Cart cart = getCart(cartId);
        List<Product> productDetails = productDAO.displayProduct();
        for (Product product : productDetails) {
            if (cart.getProduct().getId() == product.getId()) {
                 orderDetail.setProduct(product);
            }
        }
        orderDetail.setQuantity(cart.getQuantity());
    }

    /**
     * <p>
     * Method used to check if the cart exists or not
     * </p>
     *
     * @param cartId - it indicates the cart id given by the user
     * @return - it returns cart if exist else throws error message
     */
    private Cart getCart(int cartId) throws ShoppingException {
        Cart cart = cartDAO.getCartById(cartId);
        if (null == cart) {
            throw new ShoppingException("Invalid option!!! cart does not exist");
        }
        return cart;
    }

    /**
     * <p>
     * Method in which all the orders are displayed
     * </p>
     *
     * @return - it returns all the order informations
     */
    public List<Order> getOrderByUserId(int userId) throws ShoppingException {
        List<Order> orders = orderDAO.getOrderByUserId(userId);
        if (orders.isEmpty()) {
            throw new ShoppingException("No Order yet!!! Keep shopping");
        }
        return orders;
    }

    /**
     * <p>
     * Method used to delete particular order based on order id
     * </p>
     *
     * @param orderId - contains the order id choosen by the user
     */
    public void delete(int orderId) throws ShoppingException {
        Order order = orderDAO.getOrderById(orderId);
        if (null == order || order.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Order does not exist");
        }
        orderDAO.deleteOrder(orderId);
    }
}
