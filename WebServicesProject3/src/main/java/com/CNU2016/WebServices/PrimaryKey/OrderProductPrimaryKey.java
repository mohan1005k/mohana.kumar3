package com.CNU2016.WebServices.PrimaryKey;


import java.io.Serializable;

import com.CNU2016.WebServices.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.Order;

/**
 * Created by mohanakumar on 09/07/16.
 */
@Embeddable
public class OrderProductPrimaryKey implements Serializable{


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    Product productKey;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    Orders ordersKey;

    public OrderProductPrimaryKey()
    {

    }
    public OrderProductPrimaryKey(Product productKey,Orders ordersKey)
    {
            this.productKey=productKey;
            this.ordersKey=ordersKey;
    }


    public Product getProductKey() {
        return productKey;
    }

    public void setProductKey(Product productKey) {
        this.productKey = productKey;
    }

    public Orders getOrdersKey() {
        return ordersKey;
    }

    public void setOrdersKey(Orders ordersKey) {
        this.ordersKey = ordersKey;
    }





}
