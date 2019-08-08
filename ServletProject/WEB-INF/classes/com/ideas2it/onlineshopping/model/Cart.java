package com.ideas2it.onlineshopping.model;

import java.sql.Timestamp;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType; 
import javax.persistence.JoinTable;  
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.ideas2it.onlineshopping.model.Product;
import com.ideas2it.onlineshopping.model.User;

/**
 * <p>
 * This class contains all the product information present in cart which can be
 * stored
 * </p>
 */

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "CREATED_DATE")
    private Timestamp dateTime;

    @Column(name = "UPDATED_DATE")
    private Timestamp updatedDateTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Timestamp getDateTime() {
        return dateTime;
    }
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
    public Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }
    public void setUpdatedDateTime(Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
    public String toString() {
        return (id + " " + user + " " + product + " " + quantity + " " + dateTime
                + " " + updatedDateTime);
    }
}
