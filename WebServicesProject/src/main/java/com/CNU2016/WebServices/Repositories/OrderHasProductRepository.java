package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.OrderHasProduct;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//import javax.persistence.Query;

/**
 * Created by mohanakumar on 09/07/16.
 */
public interface OrderHasProductRepository extends CrudRepository<OrderHasProduct,OrderProductPrimaryKey> {

    @Query("Select sum(price) from OrderHasProduct where orderId=:id")
    Double findSum(@Param("id") int id);

    Iterable<OrderHasProduct>findByOrderProductPrimaryKeyOrdersKeyOrderId(int id);

}
