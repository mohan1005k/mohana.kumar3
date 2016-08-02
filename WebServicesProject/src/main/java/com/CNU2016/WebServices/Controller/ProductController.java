package com.CNU2016.WebServices.Controller;

import com.CNU2016.WebServices.Model.Product;
import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.*;
import com.CNU2016.WebServices.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by mohanakumar on 07/07/16.
 */
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    ProductRepository productRepository;

    /**
     *
     * @param Id -  ProductId
     *            Gets a Product by its id
     * @return JsonResponse and HttpStatus
     */

    @RequestMapping(value="/api/products/{Id}",method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable Integer Id)
    {
        logger.info("Entering getProductFunction");

        Product product=productRepository.findByProductIdAndAvailability(Id,Boolean.TRUE);
        if(product!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        logger.info("Exiting getProduct function");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
    }

    /**
     *
     * @param Id-ProductId
     *          Deletes a product if available - HttpStatus 204
     *          If the Product isn't available return HttpStatus 404
     * @return JsonResponse and HttpStatus
     */
    @RequestMapping(value="/api/products/{Id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable int Id)
    {
        logger.info("Entering deleteOrder function");

        Product product=productRepository.findByProductIdAndAvailability(Id,Boolean.TRUE);
        if(product!=null) {

            product.setAvailability(false);
            productRepository.save(product);
          //  productRepository.delete(Id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        logger.info("Exiting deleteOrder function");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));

    }

    /**
     *
     * Health api - check if an API is being hit successfully
     *
     * @return HttpStatus 200 OK
     */
    @RequestMapping(value = "/api/health", method = RequestMethod.GET)
    public ResponseEntity<?> healthCase()
    {
        return ResponseEntity.status(HttpStatus.OK).body("Health api");
    }

    /**
     *
     * @param productHelper - POJO for receiving the JSON request body
     *                      Creates a Product record
     * @return HttpStatus-201-Created on successful create
     */
    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody ProductHelper productHelper)
    {
        logger.info("Entering createProduct function");


        Product product=new Product();
        product.setProductCode(productHelper.getCode());
        product.setProductDescription(productHelper.getDescription());
        product.setQuantity(productHelper.getQuantity());

        logger.info("Exiting createProduct function");

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    /**
     *
     * @param productHelper- POJO for receiving the JSON request body
     * @param Id - ProductId
     *           Performs a 'put' update on Products table
     *           If the product is not found return HttpStatus-403:Not found
     * @return HTTPstatus 200 for successful update
     */
    @RequestMapping(value = "/api/products/{Id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct1(@RequestBody ProductHelper productHelper, @PathVariable int Id)
    {
        logger.info("Entering updateProduct function");

        Product product=productRepository.findByProductIdAndAvailability(Id,Boolean.TRUE);
        if(product==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));
        }
        Product productObject=new Product();
        productObject.setProductCode(productHelper.getCode());
        productObject.setProductDescription(productHelper.getDescription());
        productObject.setProductId(Id);

        logger.info("Exiting updateProduct function");

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productObject));
    }

    /**
     *
     * @param productHelper POJO for receiving the JSON request body
     * @param Id ProductId
     *
     *           Performs a 'patch' update on Products table
     *           If the product is not found return HttpStatus-403:Not found
     * @return
     */
    @RequestMapping(value = "/api/products/{Id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(@RequestBody ProductHelper productHelper, @PathVariable int Id)
    {
        logger.info("Entering patch of updateProduct function");

        Product product=productRepository.findByProductIdAndAvailability(Id,Boolean.TRUE);
        if(product!=null) {
            String code= productHelper.getCode();
            String description= productHelper.getDescription();
            if(code==null)
                code=product.getProductCode();
            if(description==null)
                description=product.getProductDescription();
            Product productObject=new Product();
            productObject.setProductCode(code);
            productObject.setProductDescription(description);
            productObject.setProductId(Id);
            productObject.setCost(product.getCost());
            productObject.setSellingPrice(product.getSellingPrice());
            productObject.setProductName(product.getProductName());
            productObject.setAvailability(product.getAvailability());


            return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productObject));
        }

        logger.info("Exiting patch of updateProduct function");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound("Not Found"));

    }

    /**
     *
     * @return list of all product details available
     */


    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<ProductHelper> getProducts()
    {
        logger.info("Entering getProducts function");

        List<ProductHelper> returnProduct = new ArrayList<>();

        for(Product product : productRepository.findByAvailability(Boolean.TRUE)) {
            returnProduct.add(new ProductHelper(product.getProductId(),product.getProductCode(),product.getProductDescription()));
        }

        logger.info("Exiting getProducts function");

        return returnProduct;
    }

}
