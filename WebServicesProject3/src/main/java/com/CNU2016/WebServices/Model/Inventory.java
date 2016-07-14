package com.CNU2016.WebServices.Model;

import javax.persistence.*;

import org.springframework.boot.orm.jpa.EntityScan;
import java.util.*;
/**
 * Created by mohanakumar on 09/07/16.
 */

@Entity
@Table(name="Inventory")

public class Inventory {

    @Column(name="ProductId")
    @Id
    private int productId;

    public Inventory()
    {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityId() {
        return quantityId;
    }

    public void setQuantityId(int quantityId) {
        this.quantityId = quantityId;
    }

    private int quantityId;


}
