package com.CNU2016.WebServices.Model;

import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.persistence.criteria.Order;

/**
 * Created by mohanakumar on 09/07/16.
 */

@Entity
@Table(name="Order_has_Product")
public class Order_has_Product {

    @EmbeddedId
    OrderProductPrimaryKey orderProductPrimaryKey;


    /* @Column(name="OrderId")
        private int orderId;

        @Column(name="ProductId")
        private int productId;
    */
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

    public Order_has_Product()
    {

    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Order_has_Product(OrderProductPrimaryKey orderProductPrimaryKey,int quantity,Double price)
    {
        this.orderProductPrimaryKey=orderProductPrimaryKey;
        this.quantityOrdered=quantity;
        this.price=price;

    }
  /*  public Order_has_Product(int order_OrderId,int product_productId)
    {
        this.orderId=order_OrderId;
        this.productId=product_productId;
    }
*/
    public OrderProductPrimaryKey getOrderProductPrimaryKey() {
        return orderProductPrimaryKey;
    }

    public void setOrderProductPrimaryKey(OrderProductPrimaryKey orderProductPrimaryKey) {
        this.orderProductPrimaryKey = orderProductPrimaryKey;
    }




}
