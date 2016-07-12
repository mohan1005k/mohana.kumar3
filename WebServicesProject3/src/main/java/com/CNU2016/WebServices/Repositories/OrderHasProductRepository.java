package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.Order_has_Product_Quantity;
import com.CNU2016.WebServices.Pojo.ProductQuantity;
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
    /*
    @Query("Select p.productId,p.quantity from Product AS p where p.productId IN(Select o.productId from Order_has_Product AS o where orderId=:id)")
    Iterable<ProductQuantity>findProduct(@Param("id") int id);

    @Query("Select p.productId,p.quantity from Order_has_Product op inner join op.productKey p on p.productId=op.productId where op.orderId=:id")
    Iterable<ProductQuantity>findProduct(@Param("id") int id);
*/
    /*
    @Query(value="Select productId,quantityOrdered from Order_has_Product where orderId=:id")
    Iterable<Order_has_Product_Quantity>findOrderId(int id);
*/

    Iterable<Order_has_Product>findByOrderProductPrimaryKeyOrdersKeyOrderId(int id);

}
