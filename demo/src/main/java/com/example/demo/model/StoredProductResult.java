package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class StoredProductResult {
    private List<StoredProductResponse> productAdded;
    private List<StoredProductResponse> productsNotAdded;

    public List<StoredProductResponse> getProductAdded() {
        return productAdded;
    }

    public void addProductAdded(StoredProductResponse productAdded) {
        this.productAdded.add(productAdded);
    }

    public List<StoredProductResponse> getProductsNotAdded() {
        return productsNotAdded;
    }

    public void addProductsNotAdded(StoredProductResponse productsNotAdded) {
        this.productsNotAdded.add(productsNotAdded);
    }

    public StoredProductResult() {
        this.productAdded = new ArrayList<StoredProductResponse>();
        this.productsNotAdded = new ArrayList<StoredProductResponse>();
    }
}
