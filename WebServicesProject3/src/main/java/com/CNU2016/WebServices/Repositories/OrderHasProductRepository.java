package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;

import org.springframework.data.repository.CrudRepository;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Query;
import javax.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by mohanakumar on 09/07/16.
 */
public interface OrderHasProductRepository extends CrudRepository<Order_has_Product,OrderProductPrimaryKey> {

    @Query("Select sum(price) from Order_has_Product where orderId=:id")
    Double findSum(@Param("id") int id);

  /*  @Query("Select * from Product where Product.productId IN(Select productId from Order_has_Product where orderId=:id")
    Iterable<Product>findProduct(@Param("id") int id);


    Iterable<Order_has_Product>findByOrderId(int id);
  *//*  @Query(value="Select productId from Product")
    int findById();*/
}
