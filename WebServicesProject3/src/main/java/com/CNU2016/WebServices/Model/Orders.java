package com.CNU2016.WebServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.sql.*;
import java.util.*;
/**
 * Created by mohanakumar on 08/07/16.
 */

@Entity
@Table(name="Orders")
public class Orders {

    @JsonProperty("id")
    @Column(name="OrderId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column(name="Status")
    private String status;

  /*  @Column(name="FkUserId")
    private int fkUserId;
*/
    @Column(name="DataOfOrder")
    private String dateofOrder;



    @Column(name="OrderDate")
    private Timestamp orderDate;


/*
    private List<Product>productsForOrders;

    @ManyToMany(mappedBy = "Orders")
    public List<Product> getProductsForOrders() {
        return productsForOrders;
    }

    public void setProductsForOrders(List<Product> productsForOrders) {
        this.productsForOrders = productsForOrders;
    }
*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FkUserId")
    private User user_for_order;
    public User getUser_for_order() {
        return user_for_order;
    }

    public void setUser_for_order(User user_for_order) {
        this.user_for_order = user_for_order;
    }



/*
    public int getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }
*/


    public Orders()
    {

    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Orders(int orderId,String status,String dateofOrder)
    {
        this.orderId=orderId;
        this.status=status;
    //    this.fkUserId=user_UserId;
        this.dateofOrder=dateofOrder;
        //     this.quantityOrdered=quantityOrdered;
    }

    public Orders(int orderId)
    {
        this.orderId=orderId;
    }

    public Orders(String status,User user)
    {
        this.status=status;
        this.user_for_order=user;
      //  this.fkUserId=user_UserId;
        this.dateofOrder="2015-06-01";
    }

    public Orders(String status,User user,Timestamp timestamp)
    {
        this.status=status;
        this.user_for_order=user;
        this.orderDate=timestamp;
    }
    public Orders(int orderId,String status,User user,Timestamp timestamp)
    {
        this.orderDate=timestamp;
        this.orderId=orderId;
        this.user_for_order=user;
        this.status=status;
    }
    /*
    public Orders(int orderId,String status,int user_UserId,String dateofOrder)
    {
        this.orderId=orderId;
        this.status=status;
        this.fkUserId=user_UserId;
        this.dateofOrder=dateofOrder;
   //     this.quantityOrdered=quantityOrdered;
    }

    public Orders(String status,int user_UserId)
    {
        this.status=status;
        this.fkUserId=user_UserId;
        this.dateofOrder="2015-06-01";
    }
*/
    public String getDateofOrder() {
        return dateofOrder;
    }
    public void setDateofOrder(String dateofOrder) {
        this.dateofOrder = dateofOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
