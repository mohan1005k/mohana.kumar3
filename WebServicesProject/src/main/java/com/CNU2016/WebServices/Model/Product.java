package com.CNU2016.WebServices.Model;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.*;

import org.springframework.boot.orm.jpa.EntityScan;

/**
 * Created by mohanakumar on 07/07/16.
 */
@Entity
@Table(name="Product")
public class Product {

    @Column(name="ProductId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int productId;
    private int cost;
    private String productDescription;
    private int sellingPrice;
    private String productName;
    private Boolean availability;
    private String productCode;

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

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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

    public Product(int productId,String productCode,String productDescription) {

        this.productName = null;
        this.cost = 0;
        this.sellingPrice = 0;
        this.productDescription = null;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.productId = productId;
        this.availability = Boolean.TRUE;
    }

    /*public Product(int productId,String productCode,String productDescription,)
    {

        this.productCode=productCode;
        this.productDescription=productDescription;
        this.productId=productId;
    }*/

    public Product(String productCode,String productDescription)
    {

        this.productName=null;
        this.cost=0;
        this.sellingPrice=0;
        this.productDescription=null;
        this.productCode=productCode;
        this.productDescription=productDescription;
        this.availability=Boolean.TRUE;
       // this.productId=productId;
    }

    public Product(int productId,int cost,String productDescription,int sellingPrice,String productCode,String productName,Boolean availability)
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








}
