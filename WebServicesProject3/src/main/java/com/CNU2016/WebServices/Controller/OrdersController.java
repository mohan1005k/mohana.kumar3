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



    @RequestMapping(value="/api/orders/{Idd}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrder2(@PathVariable Integer Idd)
    {
        Orders order=ordersRepository.findByOrderIdAndAvailability(Idd,true);
        if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
    }

    @RequestMapping(value="/api/orders/{Idd}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable Integer Idd)
    {
        Orders orders=ordersRepository.findByOrderIdAndAvailability(Idd,Boolean.TRUE);
        if(orders!=null) {

            orders.setAvailability(false);
            ordersRepository.save(orders);
            //  productRepository.delete(Idd);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
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
    @RequestMapping(value="/api/orders/{Idd}/orderLineItem",method=RequestMethod.POST)
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

      /*  if(product.getQuantity()<quantity)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Quantity of the Product Insufficient");
        }
        else
        {*/

          //      productRepository.save(new Product(product.getProductId(),product.getCost(),product.getProductDescription(),product.getSellingPrice(),product.getProductCode(),product.getProductName(),product.getAvailability(),product.getQuantity()-quantity));

    //    }

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User_Name and Address can't be null");

        }
        User user=userRepository.findByName(user_name);
        if(user==null)
        {

            user=userRepository.save(new User(user_name,address));
        }
        Orders order=ordersRepository.findByOrderIdAndAvailability(Idd,Boolean.TRUE);
        if(order==null)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Order doesn't exist");

        }


        Double amount=orderHasProductRepository.findSum(order.getOrderId());


        Iterable<Order_has_Product> order_has_products=orderHasProductRepository.findByOrderProductPrimaryKeyOrdersKeyOrderId(order.getOrderId());
        for(Order_has_Product order_has_product:order_has_products)
        {
            int product_id=order_has_product.getOrderProductPrimaryKey().getProductKey().getProductId();

            Product product=productRepository.findOne(product_id);
            if(product.getQuantity() < order_has_product.getQuantityOrdered())
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Quantity Insufficient");
            }
        }

        for(Order_has_Product order_has_product:order_has_products)
        {
            int product_id=order_has_product.getOrderProductPrimaryKey().getProductKey().getProductId();

            Product product=productRepository.findOne(product_id);
            int quantity=order_has_product.getQuantityOrdered();
            productRepository.save(new Product(product.getProductId(), product.getCost(), product.getProductDescription(), product.getSellingPrice(), product.getProductCode(), product.getProductName(), product.getAvailability(), product.getQuantity() - quantity));
               // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Product Quantity Insufficient")

        }


        /*
        Iterable<Order_has_Product_Quantity>order_has_products=orderHasProductRepository.findOrderId(order.getOrderId());
        Iterable<ProductQuantity>products=orderHasProductRepository.findProduct(order.getOrderId());

        HashMap<Integer,Integer>productQuantity=new HashMap<Integer,Integer>();

        for(ProductQuantity product:products)
        {
            productQuantity.put(product.getProductQuantity_productId(),product.getProductQuantity_quantity());
        }

        for(Order_has_Product_Quantity order_has_product:order_has_products)
        {
            if(order_has_product.getOrder_has_Product_Quantity_quantity() > productQuantity.get(order_has_product.getOrder_has_Product_Quantity_productId()))
            {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Product Quantity Insufficient");
            }
        }

        for(Order_has_Product_Quantity order_has_product:order_has_products) {
            Product product = productRepository.findByProductIdAndAvailability(order_has_product.getOrder_has_Product_Quantity_productId(), Boolean.TRUE);
            int quantity=order_has_product.getOrder_has_Product_Quantity_quantity();
            productRepository.save(new Product(product.getProductId(), product.getCost(), product.getProductDescription(), product.getSellingPrice(), product.getProductCode(), product.getProductName(), product.getAvailability(), product.getQuantity() - quantity));
        }
        */
      //int productId=orderHasProductRepository.findProductId(order.getOrderId());
        order=ordersRepository.save(new Orders(order.getOrderId(),"CheckOut",user,new Timestamp(new Date().getTime())));



        paymentRepository.save(new Payment("Cash on delivery",amount,order.getOrderId()));

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }



}
