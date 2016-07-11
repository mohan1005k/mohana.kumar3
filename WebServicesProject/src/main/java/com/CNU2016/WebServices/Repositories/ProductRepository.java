package com.CNU2016.WebServices.Repositories;

import com.CNU2016.WebServices.Model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;
/**
 * Created by mohanakumar on 07/07/16.
 */
public interface ProductRepository extends CrudRepository<Product,Integer>{

  // public static final String FIND_PROJECTS = "SELECT 'cnu2016_mohana_kumar FROM projects";

      Product findByProductIdAndAvailability(int a,Boolean b);
      List<Product> findByAvailability(Boolean b);


   }
