package com.CNU2016.WebServices.Model;

import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;

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
    @Column(name="Quantity")
    private int quantity;

    public Order_has_Product()
    {

    }

    public Order_has_Product(OrderProductPrimaryKey orderProductPrimaryKey,int quantity)
    {
        this.orderProductPrimaryKey=orderProductPrimaryKey;
        this.quantity=quantity;

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


/*
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
*/
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
/*
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
*/


}
