package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 08/07/16.
 */
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

import com.CNU2016.WebServices.Model.Orders;
import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.*;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.CNU2016.WebServices.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    FeedbackRepository feedbackRepository;



    @RequestMapping(value="/orders/{Idd}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable Integer Idd)
    {
        Orders order=ordersRepository.findOne(Idd);
        if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
    }

    @RequestMapping(value="/ordersuser/{Idd}",method = RequestMethod.GET)
    public User getOrdersUser(@PathVariable Integer Idd)
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


    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public ResponseEntity<?> makeOrder(@RequestBody OrderHelper orderHelper)
    {

        String name=orderHelper.getUser_name();
        User user=userRepository.findByName(name);
        Timestamp timestamp=new Timestamp(new Date().getTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersRepository.save(new Orders("Created",user,timestamp)));

    }

    @Transactional
    @RequestMapping(value="/api/orders/{Idd}/orderlineitem",method=RequestMethod.POST)
    public ResponseEntity<?>orderLineItem(@PathVariable Integer Idd,@RequestBody AddItemToOrder addItemToOrder)
    {


        int productId=addItemToOrder.getProductId();

        int quantity=addItemToOrder.getQuantity();
        int orderId=Idd;
        Product product=productRepository.findByProductIdAndAvailability(productId,true);
        if(product==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        if(ordersRepository.findOne(orderId)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found");
        }

        if(product.getQuantity()<quantity)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Quantity of the Product Insufficient");
        }
        else
        {

                productRepository.save(new Product(product.getProductId(),product.getCost(),product.getProductDescription(),product.getSellingPrice(),product.getProductCode(),product.getProductName(),product.getAvailability(),product.getQuantity()-quantity));

        }

        Double price=product.getSellingPrice()*quantity;

        return ResponseEntity.status(HttpStatus.CREATED).body(orderHasProductRepository.save(new Order_has_Product( new OrderProductPrimaryKey(new Product(productId),new Orders(orderId)),quantity,price)));

    }

    @Transactional
    @RequestMapping(value="/api/orders/{Idd}",method = RequestMethod.PATCH)
    public ResponseEntity<?>submitOrder(@PathVariable Integer Idd,@RequestBody SubmitOrder submitOrder)
    {
        String address=submitOrder.getAddress();
        String user_name=submitOrder.getUser_name();
        if(user_name==null || address==null)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User_Name and Address can't be null");

        }
        User user=userRepository.findByName(user_name);
        if(user==null)
        {

            user=userRepository.save(new User(user_name,address));
        }
        Orders order=ordersRepository.findOne(Idd);
        if(order==null)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Order doesn't exist");

        }

        Double amount=orderHasProductRepository.findSum(order.getOrderId());

      //  int productId=orderHasProductRepository.findProductId(order.getOrderId());
        order=ordersRepository.save(new Orders(order.getOrderId(),"CheckOut",user,new Timestamp(new Date().getTime())));


        paymentRepository.save(new Payment("Cash on delivery",amount,order.getOrderId()));

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }



}
