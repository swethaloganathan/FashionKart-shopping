package com.ideas2it.onlineshopping.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType; 
import javax.persistence.JoinTable;
import javax.persistence.FetchType;  
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.ideas2it.onlineshopping.model.User;
import com.ideas2it.onlineshopping.model.OrderDetail;

/**
 * <p>
 * In this class, each set of order details are stored
 * </p>
 */

@Entity
@Table(name = "USER_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private Set<OrderDetail> orderDetails;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Column(name = "STATUS")
    private Boolean isActive;

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
    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        return (id + " " + user + " " + totalPrice + " " + isActive + " " + dateTime + " "
                + updatedDateTime);
    }
}
