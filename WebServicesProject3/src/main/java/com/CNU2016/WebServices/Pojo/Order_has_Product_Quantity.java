package com.CNU2016.WebServices.Pojo;

import com.CNU2016.WebServices.Model.Order_has_Product;

/**
 * Created by mohanakumar on 13/07/16.
 */
public class Order_has_Product_Quantity {


    public Order_has_Product_Quantity()
    {

    }
    public int getOrder_has_Product_Quantity_productId() {
        return order_has_Product_Quantity_productId;
    }

    public void setOrder_has_Product_Quantity_productId(int order_has_Product_Quantity_productId) {
        this.order_has_Product_Quantity_productId = order_has_Product_Quantity_productId;
    }

    public int getOrder_has_Product_Quantity_quantity() {
        return order_has_Product_Quantity_quantity;
    }

    public void setOrder_has_Product_Quantity_quantity(int order_has_Product_Quantity_quantity) {
        this.order_has_Product_Quantity_quantity = order_has_Product_Quantity_quantity;
    }

    private int order_has_Product_Quantity_productId;
    private int order_has_Product_Quantity_quantity;
}
