package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.StoredProductResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ProductDao {

    List<Product> getProduct(String name) throws ExecutionException, InterruptedException;
    StoredProductResult setProduct(List<Product> product);
}
