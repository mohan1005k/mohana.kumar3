package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 08/07/16.
 */
import java.sql.Timestamp;
import java.util.*;

import com.CNU2016.WebServices.Model.Orders;
import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.*;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.CNU2016.WebServices.Repositories.OrderHasProductRepository;
import com.CNU2016.WebServices.Repositories.OrdersRepository;

import com.CNU2016.WebServices.Repositories.ProductRepository;
import com.CNU2016.WebServices.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderHasProductRepository orderHasProductRepository;

    /* @RequestMapping(value="/productsfororder",method=RequestMethod.GET)
     public List<Product> products()
     {
         return ordersRepository.findByOrderId();
     }
 */
    @RequestMapping(value="/orders/{Idd}",method = RequestMethod.GET)
    public ResponseEntity<?> orders(@PathVariable Integer Idd)
    {
        Orders order=ordersRepository.findOne(Idd);
        if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
    }

    @RequestMapping(value="/ordersuser/{Idd}",method = RequestMethod.GET)
    public User orders2(@PathVariable Integer Idd)
    {
        User user1=ordersRepository.findOne(Idd).getUser_for_order();
        return new User(user1.getName());
        /*if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);*/
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Orders> getOrders()
    {
        List<Orders> returnProduct = new ArrayList<>();
        //System.out.println(productRepository.findAll().toString());

        for(Orders orders : ordersRepository.findAll())
        {
            returnProduct.add(new Orders(orders.getOrderId(),orders.getStatus(),orders.getDateofOrder()));
        }
        return returnProduct;
    }


    @RequestMapping(value = "/createorder2", method = RequestMethod.POST)
    public ResponseEntity<?> orders3(@RequestBody OrderHelper orderHelper)
    {

        String email=orderHelper.getEmailId();
        User user=userRepository.findByEmail(email);
        User user2;
        if(user==null)
        {
             user2=new User(email);
             userRepository.save(user2);
        }
        else {
            user2 = user;
        }
        Timestamp timestamp=new Timestamp(new Date().getTime());
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersRepository.save(new Orders("Created",user2,timestamp)));

    }


    @RequestMapping(value="/additemtoorder",method=RequestMethod.POST)
    public ResponseEntity<?>orders4(@RequestBody AddItemToOrder addItemToOrder)
    {


        int productId=addItemToOrder.getProductId();

        int quantity=addItemToOrder.getQuantity();
        int orderId=addItemToOrder.getOrderId();

        if(productRepository.findOne(productId)==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        if(ordersRepository.findOne(orderId)==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderHasProductRepository.save(new Order_has_Product( new OrderProductPrimaryKey(new Product(productId),new Orders(orderId)),quantity)));

    }




}
