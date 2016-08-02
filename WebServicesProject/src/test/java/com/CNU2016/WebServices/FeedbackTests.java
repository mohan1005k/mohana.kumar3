package com.CNU2016.WebServices;

/**
 * Created by mohanakumar on 14/07/16.
 */
import com.CNU2016.WebServices.Pojo.*;
import com.CNU2016.WebServices.Repositories.FeedbackRepository;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class FeedbackTests extends IntegrationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    FeedbackRepository feedbackRepository;
    private int feedbackId;
    @org.junit.Before
    public void setup() {


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }


    @After
    public void tearDown()
    {
      // feedbackRepository.delete(feedbackId);
    }

    @Test
    public void createFeedback() throws Exception
    {
        String api="/api/contactus";

        FeedbackContent feedbackContent =new FeedbackContent();
        feedbackContent.setDescription("Good Service");
        Gson gson=new Gson();
        String json = gson.toJson(feedbackContent);


        MvcResult mvcResult= mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject=new JSONObject(result);
        String createdId=jsonObject.getString("feedbackId");
        System.out.println(createdId);
        feedbackId=Integer.parseInt(createdId);
        System.out.println(feedbackId);

    }

    @Test
    public void createFeedbackWithoutDescription() throws Exception
    {
        String api="/api/contactus";

        FeedbackContent feedbackContent =new FeedbackContent();
        //feedbackContent.setDescription("Good Service");
        Gson gson=new Gson();
        String json = gson.toJson(feedbackContent);


        MvcResult mvcResult= mockMvc.perform(post(api)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject=new JSONObject(result);
        String createdId=jsonObject.getString("feedbackId");
        System.out.println(createdId);
        feedbackId=Integer.parseInt(createdId);
        System.out.println(feedbackId);

    }


}