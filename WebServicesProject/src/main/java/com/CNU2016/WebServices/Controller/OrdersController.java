package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 08/07/16.
 */

import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.AddItemToOrder;
import com.CNU2016.WebServices.Pojo.NotFound;
import com.CNU2016.WebServices.Pojo.OrderHelper;
import com.CNU2016.WebServices.Pojo.SubmitOrder;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.CNU2016.WebServices.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

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


    /**
     * Get the order details of one particular OrderId
     * @param Id -OrderId
     * @return Json object of the Orders' record retrieved with HTTPstatus-200 if present.
     *          Else returns HttpStatus Not found
     */
    @RequestMapping(value="/api/orders/{Id}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrder2(@PathVariable Integer Id)
    {
        logger.info("Entering getOrder function");

        Orders order=ordersRepository.findByOrderIdAndAvailability(Id,Boolean.TRUE);
        if(order!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        logger.info("Exiting getOrder function");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
    }

    /**
     *
     * @param Id - OrderId
     *           Deletes the OrderRecord with OrderId by setting the availability status to false
     * @return HttpStatus-203 on succesful delete
     *          else HttpStatus-403-Not found
     */
    @RequestMapping(value="/api/orders/{Id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable Integer Id)
    {
        logger.info("Entering deleteOrderFunction");

        Orders orders=ordersRepository.findByOrderIdAndAvailability(Id,Boolean.TRUE);
        if(orders!=null) {

            orders.setAvailability(false);
            ordersRepository.save(orders);
            //  productRepository.delete(Id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        logger.info("Exiting deleteOrderFunction");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
    }

    /**
     *
     * @param orderHelper - POJO for receiving the JSON request body
     * @return HttpStatus-201 on successful create with JSON response of the created object
     */


    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public ResponseEntity<?> makeOrder(@RequestBody OrderHelper orderHelper)
    {

        logger.info("Entering makeOrder function");

        String name=orderHelper.getUser_name();
        User user=userRepository.findByName(name);
        Timestamp timestamp=new Timestamp(new Date().getTime());

        Orders orderObject=new Orders();
        orderObject.setStatus("Created");
        orderObject.setUser_for_order(user);
        orderObject.setOrderDate(timestamp);

        logger.info("Exiting makeOrder function");

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersRepository.save(orderObject));

    }

    /**
     *
     * @param Id - OrderId
     * @param addItemToOrder -POJO for receiving the JSON request body
     *          Adds an product to one particular orderId with the specified quantity.
     *          Checks for dependencies with Orders and Product table. Returns HttpStatus 403 if the specified Order or Product doesn't exist
     *                       Else return HttpStatus-201 on successful create
     * @return
     */

    @RequestMapping(value="/api/orders/{Id}/orderLineItem",method=RequestMethod.POST)
    public ResponseEntity<?>orderLineItem(@PathVariable Integer Id,@RequestBody AddItemToOrder addItemToOrder)
    {

        logger.info("Entering post method of OrderLineItem");

        int productId=addItemToOrder.getProduct_id();

        int quantity=addItemToOrder.getQty();
        int orderId=Id;
        Product product=productRepository.findByProductIdAndAvailability(productId,Boolean.TRUE);
        if(ordersRepository.findByOrderIdAndAvailability(orderId,Boolean.TRUE)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Not Found");
        }

        if(product==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }



        Double price=(product.getSellingPrice())*quantity;
        Product productObject=new Product();
        productObject.setProductId(productId);

        OrderHasProduct orderHasProduct=new OrderHasProduct();
        orderHasProduct.setOrderProductPrimaryKey(new OrderProductPrimaryKey(productObject,new Orders(orderId)));
        orderHasProduct.setPrice(price);
        orderHasProduct.setQuantityOrdered(quantity);

        logger.info("Exiting post method of OrderLineItem");

        return ResponseEntity.status(HttpStatus.CREATED).body(orderHasProductRepository.save(orderHasProduct));

    }

    /**
     *
     * @param Id -OrderId
     * @param submitOrder POJO for receiving the JSON request body
     *
     *             Submits an Order collecting info from the OrderLineItem table.
     *                    Checks for sufficient availability in the Product table for the products needed by the order.In case
     *                    of any in-sufficiency of quantities return HTTPstatus.400
     *             Adds a record to the Paymetn table and links it with the OrderId
     * @return  HttpStatus 201 on successful submission of an order
     */

    @Transactional
    @RequestMapping(value="/api/orders/{Id}",method = RequestMethod.PATCH)
    public ResponseEntity<?>submitOrder(@PathVariable Integer Id,@RequestBody SubmitOrder submitOrder)
    {
        logger.info("Entering submitOrder function");

        String address=submitOrder.getAddress();
        String user_name=submitOrder.getUser_name();
        if(user_name==null || address==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User_Name and Address can't be null");

        }
        User user=userRepository.findByName(user_name);
        if(user==null)
        {
            User userObject=new User();
            userObject.setName(user_name);
            userObject.setAddressLine1(address);
            user=userRepository.save(userObject);
        }
        Orders order=ordersRepository.findByOrderIdAndAvailability(Id,Boolean.TRUE);
        //changed from forbidden to not found
        if(order==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order doesn't exist");

        }


        Double amount=orderHasProductRepository.findSum(order.getOrderId());


        Iterable<OrderHasProduct> order_has_products=orderHasProductRepository.findByOrderProductPrimaryKeyOrdersKeyOrderId(order.getOrderId());
        for(OrderHasProduct order_has_product:order_has_products)
        {
            int product_id=order_has_product.getOrderProductPrimaryKey().getProductKey().getProductId();

            Product product=productRepository.findByProductIdAndAvailability(product_id,Boolean.TRUE);
            if(product.getQuantity() < order_has_product.getQuantityOrdered())
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Quantity Insufficient");
            }
        }

        for(OrderHasProduct order_has_product:order_has_products)
        {
            int product_id=order_has_product.getOrderProductPrimaryKey().getProductKey().getProductId();

            Product product=productRepository.findOne(product_id);
            int quantity=order_has_product.getQuantityOrdered();
            Product productObject=new Product();
            productObject.setProductCode(product.getProductCode());
            productObject.setProductDescription(product.getProductDescription());
            productObject.setProductId(product.getProductId());
            productObject.setCost(product.getCost());
            productObject.setSellingPrice(product.getSellingPrice());
            productObject.setProductName(product.getProductName());
            productObject.setAvailability(product.getAvailability());
            productObject.setQuantity(product.getQuantity() - quantity);
            productRepository.save(productObject);
        }

        Orders orderObject=new Orders();
        orderObject.setStatus("Created");
        orderObject.setOrderId(order.getOrderId());
        orderObject.setUser_for_order(user);
        orderObject.setOrderDate(new Timestamp(new Date().getTime()));

        order=ordersRepository.save(orderObject);


        Payment payment=new Payment();
        payment.setAmount(amount);
        payment.setMode("COD");
        payment.setOrder_OrderId(order.getOrderId());
        paymentRepository.save(payment);

        logger.info("Exiting submitOrder function");

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }



}
