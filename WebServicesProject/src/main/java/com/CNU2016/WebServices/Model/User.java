package com.CNU2016.WebServices.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mohanakumar on 08/07/16.
 */


import javax.persistence.*;

import java.util.*;
@Entity
@Table(name="User")
public class User {

    @Column(name="UserId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name="Email")
    private String email;

    @Column(name="ContactFirstName")
    private String contactFirstName;

    @Column(name="ContactLastName")
    private String contactLastName;

    @Column(name="Phone")
    private String phone;


    @Column(name="AddressLine1")
    private String addressLine1;

    @Column(name="AddressLine2")
    private String addressLine2;

    @Column(name="City")
    private String city;

    @Column(name="State")
    private String state;

    @Column(name="PostalCode")
    private int postalCode;

    @Column(name="Name")
    private String name;

    @Column(name="Country")
    private String country;

/*
    @OneToMany(mappedBy = "user_for_order",fetch = FetchType.LAZY)
    List<Orders>orders_of_users;
    public List<Orders> getOrders_of_users() {
        return orders_of_users;
    }

    public void setOrders_of_users(List<Orders> orders_of_users) {
        this.orders_of_users = orders_of_users;
    }
*/
    public User(int userId,String name)
    {
        this.userId=userId;
        this.name=name;
    }


    public User()
    {

    }

    public User(String email)
    {
        this.email=email;
        this.postalCode=1;
    }
    /*public User(String name)
    {
        this.name=name;
    }*/
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailId) {
        this.email = emailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
