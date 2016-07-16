package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.Orders;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mohanakumar on 08/07/16.
 */
public interface OrdersRepository extends CrudRepository<Orders,Integer>{

    Orders findByOrderIdAndAvailability(int a, Boolean b);
}
