package com.example.demo.api;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import com.example.demo.model.StoredProductResponse;
import com.example.demo.model.StoredProductResult;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "/api/v1/products", produces = "application/json; charset=UTF-8")
public class ProductsController {

    @Autowired
    private ProductDao productDao;

    public ProductsController() {
    }

    @PostMapping("/insertProduct")
    public HttpEntity insertProduct(@RequestBody List<Product> products){

        StoredProductResult result = productDao.setProduct(products);

        return new ResponseEntity(new Gson().toJson(result), HttpStatus.OK);
    }

    @GetMapping("/getProduct/{name}")
    public HttpEntity getProduct(@PathVariable("name") String name) throws ExecutionException, InterruptedException {
        List<Product> result = new ArrayList<Product>();
        result = productDao.getProduct(name);
        return new ResponseEntity(new Gson().toJson(result), HttpStatus.OK);
    }



}
