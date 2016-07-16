package com.CNU2016.WebServices;

/**
 * Created by mohanakumar on 13/07/16.
 */

import com.CNU2016.WebServices.Model.Orders;
import com.CNU2016.WebServices.Model.Product;
import com.CNU2016.WebServices.Pojo.AddItemToOrder;
import com.CNU2016.WebServices.Pojo.OrderHelper;
import com.CNU2016.WebServices.Pojo.SubmitOrder;
import com.CNU2016.WebServices.Repositories.OrderHasProductRepository;
import com.CNU2016.WebServices.Repositories.OrdersRepository;
import com.CNU2016.WebServices.Repositories.ProductRepository;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class OrdersTests extends IntegrationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderHasProductRepository orderHasProductRepository;

    private MockMvc mockMvc;

    private Product product;

    private int defaultProductQuantity;

    private int defaultOrderQuantity;
    private Orders orders;

    @org.junit.Before
    public void setup()
    {


        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        defaultProductQuantity=100;
        Product productObject=new Product();
        productObject.setQuantity(defaultProductQuantity);
        productObject.setAvailability(Boolean.TRUE);
        product=productRepository.save(productObject);


        defaultOrderQuantity=50;
        orders=ordersRepository.save(new Orders());

    }


    @After
    public void tearDown()
    {
     //   System.out.println("-------------teardown");
     //   productRepository.delete(product.getProductId());
      //  ordersRepository.delete(orders.getOrderId());
    }

    @Test
    public void getOrderTest() throws Exception
    {
        mockMvc.perform(get("/api/orders/"+orders.getOrderId())).andExpect(status().isOk());

    }
    @Test
    public void getNonExistingOrderTest() throws Exception
    {
        mockMvc.perform(get("/api/orders/"+(orders.getOrderId()+1))).andExpect(status().isNotFound());

    }

    @Test
    public void createOrder() throws Exception
    {
        String api="/api/orders/";
        OrderHelper orderHelper=new OrderHelper();
        Gson gson=new Gson();
        String json = gson.toJson(orderHelper);

        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void deleteOrder() throws Exception
    {
        String api="/api/orders/"+ordersRepository.save(new Orders()).getOrderId();
        mockMvc.perform(delete(api)).andExpect(status().isNoContent());

        mockMvc.perform(delete(api)).andExpect(status().isNotFound());



    }
    @Test
    public void addOrderLineItemTest() throws Exception
    {

        AddItemToOrder addItemToOrder=new AddItemToOrder();
        addItemToOrder.setQty (defaultOrderQuantity);
        addItemToOrder.setProduct_id (product.getProductId());

        Gson gson = new Gson();
        String json = gson.toJson(addItemToOrder);
        String api="/api/orders/"+orders.getOrderId()+"/orderLineItem";
        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isCreated()).andReturn();
    }
    @Test
    public void addOrderLineItemTestWithoutExistingOrder() throws Exception
    {

        AddItemToOrder addItemToOrder=new AddItemToOrder();
        addItemToOrder.setQty (defaultOrderQuantity);
        addItemToOrder.setProduct_id (product.getProductId());

        Gson gson = new Gson();
        String json = gson.toJson(addItemToOrder);
        String api="/api/orders/"+(orders.getOrderId()+1)+"/orderLineItem";
        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isNotFound()).andReturn();
    }
    @Test
    public void addOrderLineItemTestWithoutExistingProduct() throws Exception
    {

        AddItemToOrder addItemToOrder=new AddItemToOrder();
        addItemToOrder.setQty (defaultOrderQuantity);
        addItemToOrder.setProduct_id (product.getProductId()+1);

        Gson gson = new Gson();
        String json = gson.toJson(addItemToOrder);
        String api="/api/orders/"+(orders.getOrderId())+"/orderLineItem";
        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isNotFound()).andReturn();
    }
    public void addProductToOrder() throws Exception
    {
        AddItemToOrder addItemToOrder=new AddItemToOrder();
        addItemToOrder.setQty (defaultOrderQuantity);
        addItemToOrder.setProduct_id (product.getProductId());

        Gson gson = new Gson();
        String json = gson.toJson(addItemToOrder);
        String api="/api/orders/"+orders.getOrderId()+"/orderLineItem";
        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isCreated()).andReturn();

    }


    @Test
    public void submitOrderTestWithSufficientQuantity() throws Exception
    {

        addProductToOrder();
        Gson gson = new Gson();
        SubmitOrder submitOrder=new SubmitOrder();
        submitOrder.setAddress("Address1");
        submitOrder.setUser_name("Name1");

        Gson gson2 = new Gson();
        String json2 = gson.toJson(submitOrder);
        String api2="/api/orders/"+orders.getOrderId();

        mockMvc.perform(patch(api2).contentType(MediaType.APPLICATION_JSON_UTF8).content(json2)).andExpect(status().isOk());


    }

    @Test
    public void submitOrderTestWithoutOrder() throws Exception
    {

        addProductToOrder();
        Gson gson = new Gson();
        SubmitOrder submitOrder=new SubmitOrder();
        submitOrder.setAddress("Address1");
        submitOrder.setUser_name("Name1");

        String nonExistingOrderId="0";
        Gson gson2 = new Gson();
        String json2 = gson.toJson(submitOrder);
        String api2="/api/orders/"+nonExistingOrderId;

        mockMvc.perform(patch(api2).contentType(MediaType.APPLICATION_JSON_UTF8).content(json2)).andExpect(status().isNotFound());


    }

    @Test
    public void submitOrderTestWithInSufficientQuantity() throws Exception
    {

        addProductToOrder();
         Gson gson=new Gson();
        int new_product_quantity=0;
        Product productObject=new Product();
        productObject.setProductCode(product.getProductCode());
        productObject.setProductDescription(product.getProductDescription());
        productObject.setProductId(product.getProductId());
        productObject.setCost(product.getCost());
        productObject.setSellingPrice(product.getSellingPrice());
        productObject.setProductName(product.getProductName());
        productObject.setAvailability(product.getAvailability());
        productObject.setQuantity(new_product_quantity);
        productRepository.save(productObject);
        SubmitOrder submitOrder=new SubmitOrder();
        submitOrder.setAddress("Address2");
        submitOrder.setUser_name("Name2");

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(submitOrder);
        String api2="/api/orders/"+orders.getOrderId();

        mockMvc.perform(patch(api2).contentType(MediaType.APPLICATION_JSON_UTF8).content(json2)).andExpect(status().isBadRequest());


    }

    @Test
    public void submitOrderTestWithoutUser() throws Exception
    {
        addProductToOrder();
        Gson gson=new Gson();
        int new_product_quantity=0;
        Product productObject=new Product();
        productObject.setProductCode(product.getProductCode());
        productObject.setProductDescription(product.getProductDescription());
        productObject.setProductId(product.getProductId());
        productObject.setCost(product.getCost());
        productObject.setSellingPrice(product.getSellingPrice());
        productObject.setProductName(product.getProductName());
        productObject.setAvailability(product.getAvailability());
        productObject.setQuantity(new_product_quantity);

        productRepository.save(productObject);
        SubmitOrder submitOrder=new SubmitOrder();
        submitOrder.setAddress("Address2");
        //submitOrder.setUser_name("Name2");

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(submitOrder);
        String api2="/api/orders/"+orders.getOrderId();

        mockMvc.perform(patch(api2).contentType(MediaType.APPLICATION_JSON_UTF8).content(json2)).andExpect(status().isBadRequest());


    }

    @Test
    public void submitOrderTestWithoutAddress() throws Exception
    {
        addProductToOrder();
        Gson gson=new Gson();
        int new_product_quantity=0;
        Product productObject=new Product();
        productObject.setProductCode(product.getProductCode());
        productObject.setProductDescription(product.getProductDescription());
        productObject.setProductId(product.getProductId());
        productObject.setCost(product.getCost());
        productObject.setSellingPrice(product.getSellingPrice());
        productObject.setProductName(product.getProductName());
        productObject.setAvailability(product.getAvailability());
        productObject.setQuantity(new_product_quantity);

        productRepository.save(productObject);
        SubmitOrder submitOrder=new SubmitOrder();
        //submitOrder.setAddress("Address2");
        submitOrder.setUser_name("Name3");

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(submitOrder);
        String api2="/api/orders/"+orders.getOrderId();

        mockMvc.perform(patch(api2).contentType(MediaType.APPLICATION_JSON_UTF8).content(json2)).andExpect(status().isBadRequest());


    }


}
