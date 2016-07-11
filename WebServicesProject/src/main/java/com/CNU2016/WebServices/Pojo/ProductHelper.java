package com.CNU2016.WebServices.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mohanakumar on 08/07/16.
 */

public class ProductHelper {


    public ProductHelper()
    {

    }

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

    private String code;
    private String description;


}
