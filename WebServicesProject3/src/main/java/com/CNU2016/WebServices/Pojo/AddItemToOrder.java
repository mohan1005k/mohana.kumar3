package com.CNU2016.WebServices.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mohanakumar on 09/07/16.
 */
public class AddItemToOrder {


  //  private int orderId;
    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("qty")
    private int quantity;

   /* public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
*/
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AddItemToOrder()
    {

    }

}
