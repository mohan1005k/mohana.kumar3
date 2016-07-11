package com.CNU2016.WebServices.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CNU2016.WebServices.Model.*;
import org.springframework.data.jpa.repository.Query;



import java.util.*;

/**
 * Created by mohanakumar on 09/07/16.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);
    User findDistinctByName(String name);
    User findByName(String name);
}
