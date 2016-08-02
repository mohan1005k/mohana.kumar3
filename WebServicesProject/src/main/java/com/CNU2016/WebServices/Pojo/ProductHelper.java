package com.CNU2016.WebServices.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mohanakumar on 08/07/16.
 */

public class ProductHelper {




    @JsonProperty("qty")
    private int qty;
    public ProductHelper()
    {

    }
/*
    public ProductHelper(Integer productId, String productCode, String productDescription, int quantity)
    {
        this.id=productId;
        this.code=productCode;
        this.description=productDescription;
        this.qty=quantity;
    }
*/
    public ProductHelper(Integer productId, String productCode, String productDescription)
    {
        this.id=productId;
        this.code=productCode;
        this.description=productDescription;

    }


    private  Integer id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return qty;
    }

    public void setQuantity(int quantity) {
        this.qty = quantity;
    }
    private String code;
    private String description;


}
