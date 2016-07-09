package com.CNU2016.WebServices.Controller;

import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import com.CNU2016.WebServices.Model.Product;

import com.CNU2016.WebServices.Model.Product1;
import com.CNU2016.WebServices.Pojo.NotFound;
import com.CNU2016.WebServices.Repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mohanakumar on 07/07/16.
 */
@RestController
public class WebServicesController {

    @Autowired
    ProductRepository productRepository;
    @RequestMapping("/products1")
    public List<Product> products()
    {
        //return "hi";
        //List<Product> persons =
        List<Product>resultRecords=new ArrayList<>();
        return resultRecords;
              //  resultRecords=productRepository.findAll();
    }



    @RequestMapping(value="/api/products/{Idd}",method = RequestMethod.GET)
    public ResponseEntity<?> products(@PathVariable Integer Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
    }

    @RequestMapping(value="/api/products/{Idd}",method = RequestMethod.DELETE)
    public ResponseEntity<?> products(@PathVariable int Idd)
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

    @RequestMapping(value="/api/{Idd}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int Idd)
    {

        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product!=null) {

            productRepository.delete(Idd);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found to delete");

    }

    @RequestMapping(value="/api/products3")
    public List<Product>products2()
    {
        List<Product>resultRecords=new ArrayList<>();
        Iterable<Product> it=productRepository.findAll();
        Iterator iterator=it.iterator();
        while(iterator.hasNext())
        {
            resultRecords.add((Product)iterator.next());


        }
        return  resultRecords;
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public ResponseEntity<?> products3(@RequestBody Product1 product1)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(new Product(product1.getCode(),product1.getDescription())));
    }

    @RequestMapping(value = "/api/products/{Idd}", method = RequestMethod.PUT)
    public ResponseEntity<?> products4(@RequestBody Product1 product1,@PathVariable int Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(new Product(Idd,product1.getCode(),product1.getDescription())));
    }

    @RequestMapping(value = "/api/products/{Idd}", method = RequestMethod.PATCH)
    public ResponseEntity<?> products5(@RequestBody Product1 product1,@PathVariable int Idd)
    {
        Product product=productRepository.findByProductIdAndAvailability(Idd,Boolean.TRUE);
        if(product!=null) {
            String code=product1.getCode();
            String description=product1.getDescription();
            if(code==null)
                code=product.getProductCode();
            if(description==null)
                description=product.getProductDescription();
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(new Product(Idd, product.getCost(), description, product.getSellingPrice(), code, product.getProductName(), product.getAvailability())));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));

    }

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<Product1> getProducts()
    {
        List<Product1> returnProduct = new ArrayList<>();
        //System.out.println(productRepository.findAll().toString());

        for(Product product : productRepository.findByAvailability(Boolean.TRUE)) {
            returnProduct.add(new Product1(product.getProductId(),product.getProductCode(),product.getProductDescription()));
        }
        return returnProduct;
    }

}
