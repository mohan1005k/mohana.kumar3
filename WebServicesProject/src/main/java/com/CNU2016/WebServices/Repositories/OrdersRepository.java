package com.CNU2016.WebServices.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CNU2016.WebServices.Model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

/**
 * Created by mohanakumar on 08/07/16.
 */
public interface OrdersRepository  extends CrudRepository<Orders,Integer>{
}
