package com.CNU2016.WebServices.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mohanakumar on 09/07/16.
 */
public class AddItemToOrder {


  //  private int orderId;
    @JsonProperty("product_id")
    private int product_id;


    @JsonProperty("qty")
    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


   /* public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
*/
    public AddItemToOrder()
    {

    }

}
