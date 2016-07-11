package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 09/07/16.
 */

import java.util.*;

import com.CNU2016.WebServices.Model.User;

import com.CNU2016.WebServices.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

   /* @RequestMapping(value="/userorder/{Idd}",method = RequestMethod.GET)
    public List<Orders> user2(@PathVariable Integer Idd)
    {


        List<Orders> returnProduct = new ArrayList<>();
        for(Orders orders : userRepository.findOne(Idd).getOrders_of_users())
        {
            returnProduct.add(new Orders(orders.getOrderId(),orders.getStatus(),orders.getFkUserId(),orders.getDateofOrder()));
        }
        return returnProduct;
        /*if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);*/
 /*   }
*/
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> user1()
    {
        List<User> returnProduct = new ArrayList<>();
        //System.out.println(productRepository.findAll().toString());

        for(User user : userRepository.findAll())
        {
            returnProduct.add(new User(user.getUserId(),user.getName()));
        }
        return returnProduct;
    }
}
