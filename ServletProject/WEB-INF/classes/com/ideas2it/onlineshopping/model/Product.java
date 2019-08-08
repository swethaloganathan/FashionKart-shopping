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

import com.ideas2it.onlineshopping.model.User;

/**
 * <p>
 * Class is used to store each product information, which can be used for
 * further operation
 * </p>
 */

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "CREATED_BY")
    private User user;

    @Column(name = "SKU_ID")
    private String sku;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "SELLING_PRICE")
    private int sellingPrice;

    @Column(name = "MRP")
    private int mrp;

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
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public int getMrp() {
        return mrp;
    }
    public void setMrp(int mrp) {
        this.mrp = mrp;
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
        return (id + " " + user + " " + sku + " " + productName + " " + description 
                + " " + price + " " + sellingPrice + " " + mrp + " " + dateTime
                + " " + updatedDateTime);
    }
}
