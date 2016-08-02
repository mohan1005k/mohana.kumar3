package com.CNU2016.WebServices.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by mohanakumar on 07/07/16.
 */
@Entity
@Table(name="Product")
public class Product {

    @JsonProperty("id")
    @Column(name="ProductId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int productId;
    private Double cost;

    @JsonProperty("description")
    private String productDescription;
    private Double sellingPrice;
    private String productName;
    private Boolean availability;

    @JsonProperty("qty")
    private int quantity;

    @JsonProperty("code")
    private String productCode;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getProductCode() {
        return productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public Product()
    {

        this.availability=Boolean.TRUE;
        this.quantity=0;
        this.sellingPrice=0.0;
        this.cost=0.0;
    }







}
