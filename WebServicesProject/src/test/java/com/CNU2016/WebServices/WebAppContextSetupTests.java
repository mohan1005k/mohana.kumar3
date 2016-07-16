package com.CNU2016.WebServices;

/**
 * Created by mohanakumar on 12/07/16.
 */
import com.CNU2016.WebServices.Pojo.ProductHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class WebAppContextSetupTests extends IntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;


     private MockMvc mockMvc;

    @org.junit.Before
    public void setup()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    public void healthTestcase() throws Exception
    {
        mockMvc.perform(get("/api/health/")).andExpect(status().isOk());
    }

    //@Test
    public void getExistingProductTest() throws Exception
    {

        String productId="122823";
        mockMvc.perform(get("/api/products/"+productId)).andExpect(status().isOk())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.productName").value("2002 Suzuki XREO"));
    }

    //@Test
    public void getAllProductsTest()throws Exception
    {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }

    //@Test
    public void getNonExistingProductTest() throws Exception
    {

        String productId="12282300";
        mockMvc.perform(get("/api/products/"+productId)).andExpect(status().isNotFound())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.detail").value("Not Found"));
    }

    //@Test
    public void insertAndDelteProdcutTest()throws Exception
    {
        ProductHelper productHelper=new ProductHelper();
        productHelper.setCode("Code6");
        productHelper.setDescription("Description6");
        productHelper.setQuantity(300);

        Gson gson = new Gson();
        String json = gson.toJson(productHelper);

        MvcResult mvcResult= mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject=new JSONObject(result);
        String createdId=jsonObject.getString("id");

        mockMvc.perform(get("/api/products/"+createdId)).andExpect(status().isOk())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value("Code6"));

        mockMvc.perform(delete("/api/products/"+createdId)).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/"+createdId)).andExpect(status().isNotFound())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.detail").value("Not Found"));

    }
}
