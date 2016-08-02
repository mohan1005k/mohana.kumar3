package com.CNU2016.WebServices;

/**
 * Created by mohanakumar on 13/07/16.
 */
import com.CNU2016.WebServices.Model.Product;
import com.CNU2016.WebServices.Pojo.ProductHelper;
import com.CNU2016.WebServices.Repositories.ProductRepository;
import com.google.gson.Gson;
import org.hamcrest.core.IsNull;
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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ProductTests extends IntegrationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final Logger logger = LoggerFactory.getLogger(ProductTests.class);
    @Autowired
    ProductRepository productRepository;

    private MockMvc mockMvc;

    private  Product product;

    private String defaultDescription;
    @org.junit.Before
    public void setup()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        defaultDescription="default_desc";
        Product productObject=new Product();
        productObject.setProductDescription(defaultDescription);
        product=productRepository.save(productObject);

    }

    @After
    public void tearDown()
    {
        productRepository.delete(product);
    }

    @Test
    public void healthApiTest() throws  Exception
    {
        logger.info("Start of health test case");
        mockMvc.perform(get("/api/health")).andExpect(status().isOk());

    }

    @Test
    public void getExistingProductTest() throws Exception
    {

        mockMvc.perform(get("/api/products/"+product.getProductId())).andExpect(status().isOk());
    }

    @Test
    public void createProduct() throws Exception
    {
        String api="/api/products/";
        ProductHelper productHelper=new ProductHelper();
        Gson gson=new Gson();
        String json = gson.toJson(productHelper);

        MvcResult mvcResult=mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isCreated()).andReturn();

    }
    @Test
    public void getNonExistingProduct()throws Exception
    {
        mockMvc.perform(delete("/api/products/"+product.getProductId())).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/"+product.getProductId())).andExpect(status().isNotFound());
    }

    @Test
    public void getAllProductsTest()throws Exception
    {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }

    @Test
    public void patchProductTest() throws Exception
    {
        String testCode="PatchCode";
        ProductHelper productHelper=new ProductHelper();
        productHelper.setCode(testCode);

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);

        mockMvc.perform(patch("/api/products/"+product.getProductId())
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());



        mockMvc.perform(get("/api/products/"+product.getProductId())).andExpect(status().isOk())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(testCode)).andExpect(jsonPath("$.description").value(defaultDescription));


    }

    @Test
    public void patchNonExistingProductTest() throws Exception
    {
        String testCode="PatchCode";
        ProductHelper productHelper=new ProductHelper();
        productHelper.setCode(testCode);

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);

        mockMvc.perform(patch("/api/products/"+product.getProductId()+"1")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());


    }
    @Test
    public void patchWithoutProductCodeProductTest() throws Exception
    {
        String testCode="PatchCode";
        ProductHelper productHelper=new ProductHelper();

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);

        mockMvc.perform(patch("/api/products/"+product.getProductId()+"1")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());


    }
    @Test
    public void putProductTest() throws Exception
    {
        String testCode="PutCode";
        ProductHelper productHelper=new ProductHelper();
        productHelper.setCode(testCode);

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);
     //   String productId="0";

        mockMvc.perform(put("/api/products/"+product.getProductId())
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());



        mockMvc.perform(get("/api/products/"+product.getProductId())).andExpect(status().isOk())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(testCode)).andExpect(jsonPath("$.description").value(IsNull.nullValue()));


    }
    @Test
    public void putNonExistingProductTest() throws Exception
    {
        String testCode="PutCode";
        ProductHelper productHelper=new ProductHelper();
        productHelper.setCode(testCode);

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);

       // String productId="0";
        mockMvc.perform(put("/api/products/"+product.getProductId()+"1")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());



    }
    @Test
    public void deleteProductTest() throws Exception
    {
        mockMvc.perform(delete("/api/products/"+product.getProductId())).andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/products/"+product.getProductId())).andExpect(status().isNotFound());

    }


}
