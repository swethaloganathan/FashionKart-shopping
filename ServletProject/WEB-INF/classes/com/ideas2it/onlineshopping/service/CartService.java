package com.ideas2it.onlineshopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlineshopping.dao.CartDAO;
import com.ideas2it.onlineshopping.dao.ProductDAO;
import com.ideas2it.onlineshopping.model.Cart;
import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.utils.DateUtils;
import com.ideas2it.onlineshopping.utils.ProductDAOInitializer;
import com.ideas2it.onlineshopping.utils.CartDAOInitializer;
import com.ideas2it.onlineshopping.exception.ShoppingException;

/**
 * <p>
 * It contains all the logical operations related to cart
 * </p>
 */
public class CartService {

    private CartDAO cartDAO = CartDAOInitializer.getInstance();
    private ProductDAO productDAO = ProductDAOInitializer.getInstance();
    private Cart cart;
    private Product product;

    public Product getProductById(int productId) throws ShoppingException {
        Product product = productDAO.getProductById(productId);
        if (null == product || product.getIsActive() == Boolean.FALSE) {
            throw new ShoppingException("Product not in Stock");
        }
        return product;
    }

    /**
     * <p>
     * Method in which each cart informations are created
     * </p>
     *
     * @param cart - contains each individual cart information
     */
    public void create(Cart cart, int productId) throws ShoppingException {
        Timestamp dateTime = DateUtils.getDateTime();
        cart.setDateTime(dateTime);
        cart.setUpdatedDateTime(dateTime);
        cart.setProduct(getProductById(productId));
        cartDAO.createCart(cart);
    }

    /**
     * <p>
     * It is used to get cart details of particular user based on user id
     * </p>
     *
     * @param userId - indicates the user id entered by user
     * @return - it returns all the cart details for particular user
     */
    public List<Cart> getCartByUserId(int userId) throws ShoppingException {
        List<Cart> carts = cartDAO.getCartByUserId(userId);
        if (carts.isEmpty()) {
            throw new ShoppingException("No products in your cart, please add product first");
        }
        return carts;
    }


    /**
     * <p>
     * Method used to check if the cart exists or not
     * </p>
     *
     * @param cartId - it indicates the cart id given by the user
     * @return - it returns cart details
     */
    public Cart getCart(int cartId) throws ShoppingException {
        Cart cart = cartDAO.getCartById(cartId);
        if (null == cart) {
            throw new ShoppingException("Data does not exist");
        }
        return cart;
    }

    /**
     * <p>
     * It is used to update particular product quantity based on cart id
     * </p>
     *
     * @param cart - it represents the cart information
     */
    public void updateDetail(Cart cart, int productId, int cartId, String date) throws ShoppingException {
        getCart(cartId);
        cart.setId(cartId);
        cart.setProduct(getProductById(productId));
        Timestamp createdDate = DateUtils.convertDate(date);
        cart.setDateTime(createdDate);
        Timestamp dateTime = DateUtils.getDateTime();
        cart.setUpdatedDateTime(dateTime);
        cartDAO.updateCart(cart);
    }

    /**
     * <p>
     * In this method, particular cart can be identified and removed using 
     * cart id
     * </p>
     *
     * @param cartId - it represents the cart id of the product
     */
    public void delete(int cartId) throws ShoppingException {
        getCart(cartId);
        cartDAO.deleteCart(cartId);
    }
}
