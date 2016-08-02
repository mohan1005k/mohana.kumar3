package com.CNU2016.WebServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

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




    @Column(name="OrderDate")
    private Timestamp orderDate;


    private Boolean availability;

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }



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




    public Orders()
    {
        this.availability=Boolean.TRUE;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public Orders(int orderId)
    {
        this.orderId=orderId;
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
