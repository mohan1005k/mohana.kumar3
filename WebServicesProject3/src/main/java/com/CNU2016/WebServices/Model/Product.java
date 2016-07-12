package com.CNU2016.WebServices.Model;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.orm.jpa.EntityScan;
import java.util.*;
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

/*
    private List<Orders>orders;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Order_has_Product", joinColumns = @JoinColumn(name = "Product_ProductId", referencedColumnName = "ProductId"), inverseJoinColumns = @JoinColumn(name = "Order_OrderId", referencedColumnName = "OrderId"))
    public List<Orders> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
*/

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

    }

    public Product(int productId)
    {
        this.productId=productId;
    }
    public Product(int productId,String productCode,String productDescription) {

        this.productName = null;
        this.cost = 10.0-10.0;
        this.sellingPrice = 10.0-10.0;
        this.productDescription = null;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.productId = productId;
        this.availability = Boolean.TRUE;
    }



    public Product(String productCode,String productDescription)
    {

        this.productName=null;
        this.cost=10.0-10.0;
        this.sellingPrice=10.0-10.0;
        this.productDescription=null;
        this.productCode=productCode;
        this.productDescription=productDescription;
        this.availability=Boolean.TRUE;
       // this.productId=productId;
    }

    public Product(int productId,Double cost,String productDescription,Double sellingPrice,String productCode,String productName,Boolean availability)
    {
        this.productId=productId;
        this.cost=cost;
        this.productDescription=productDescription;
        this.sellingPrice=sellingPrice;
        this.productName=productName;
        this.availability=availability;
        this.productCode=productCode;
        //this.availability=


    }

    public Product(int productId,Double cost,String productDescription,Double sellingPrice,String productCode,String productName,Boolean availability,int quantity)
    {
        this.productId=productId;
        this.cost=cost;
        this.productDescription=productDescription;
        this.sellingPrice=sellingPrice;
        this.productName=productName;
        this.availability=availability;
        this.productCode=productCode;
        this.quantity=quantity;
        //this.availability=


    }









}
