package com.CNU2016.WebServices.Controller;

import java.util.*;
import com.CNU2016.WebServices.Model.Product;

import com.CNU2016.WebServices.Pojo.ProductHelper;
import com.CNU2016.WebServices.Pojo.NotFound;
import com.CNU2016.WebServices.Repositories.ProductRepository;


import com.google.gson.Gson;
import com.amazonaws.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/**
 * Created by mohanakumar on 07/07/16.
 */
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;



    @RequestMapping(value="/api/products/{Idd}",method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable Integer Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);


        if(product!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
    }


    @RequestMapping(value="/api/products/{Idd}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable int Idd)
    {

        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product!=null) {

            product.setAvailability(false);
            productRepository.save(product);
          //  productRepository.delete(Idd);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));

    }




    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody ProductHelper productHelper)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(new Product(productHelper.getCode(), productHelper.getDescription())));
    }

    @RequestMapping(value = "/api/products/{Idd}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct1(@RequestBody ProductHelper productHelper, @PathVariable int Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(new Product(Idd, productHelper.getCode(), productHelper.getDescription())));
    }

    @RequestMapping(value = "/api/products/{Idd}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(@RequestBody ProductHelper productHelper, @PathVariable int Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product!=null) {
            String code= productHelper.getCode();
            String description= productHelper.getDescription();
            if(code==null)
                code=product.getProductCode();
            if(description==null)
                description=product.getProductDescription();
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(new Product(Idd, product.getCost(), description, product.getSellingPrice(), code, product.getProductName(), product.getAvailability())));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));

    }

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<ProductHelper> getProducts()
    {
        List<ProductHelper> returnProduct = new ArrayList<>();

        for(Product product : productRepository.findByAvailability(Boolean.TRUE)) {
            returnProduct.add(new ProductHelper(product.getProductId(),product.getProductCode(),product.getProductDescription()));
        }
        return returnProduct;
    }

}
