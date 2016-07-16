package com.CNU2016.WebServices.Model;

import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mohanakumar on 09/07/16.
 */

@Entity
@Table(name="OrderHasProduct")
public class OrderHasProduct {

    @EmbeddedId
    OrderProductPrimaryKey orderProductPrimaryKey;


    @JsonProperty("qty")
    @Column(name="QuantityOrdered")
    private int quantityOrdered;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name="Price")
    private Double price;

    public OrderHasProduct()
    {

    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }


    public OrderProductPrimaryKey getOrderProductPrimaryKey() {
        return orderProductPrimaryKey;
    }

    public void setOrderProductPrimaryKey(OrderProductPrimaryKey orderProductPrimaryKey) {
        this.orderProductPrimaryKey = orderProductPrimaryKey;
    }




}
