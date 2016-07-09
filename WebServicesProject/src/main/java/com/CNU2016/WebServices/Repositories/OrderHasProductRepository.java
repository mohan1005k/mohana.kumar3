package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.Order_has_Product;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mohanakumar on 09/07/16.
 */
public interface OrderHasProductRepository extends CrudRepository<Order_has_Product,OrderProductPrimaryKey> {

}
