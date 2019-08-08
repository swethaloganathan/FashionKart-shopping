package com.ideas2it.onlineshopping.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;  
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.ideas2it.onlineshopping.model.Role;

/**
 * <p>
 * This class contains all the user information which are stored and retrieved
 * for further operations
 * </p>
 */

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLE", joinColumns={@JoinColumn(name = "USER_ID", referencedColumnName="ID")},
               inverseJoinColumns={@JoinColumn(name = "ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL_ID")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

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
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
        return (id + " " + username + " " + password + " " + roles + " " + name + " "
                + email + " " + phone + " " + address + " " + isActive + " "
                + dateTime + " " + updatedDateTime);
    }
}
