package com.example.demo.dao;


import com.example.demo.model.Product;
import com.example.demo.model.StoredProductResponse;
import com.example.demo.model.StoredProductResult;
import com.example.demo.service.FBinitialize;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository("firestoreDao")
public class FireStoreDataAccess implements ProductDao {

    @Autowired
    private FBinitialize firebase;
    private List<Product> docData;

    @Override
    public List<Product> getProduct(String name) {

        docData = new ArrayList<Product>();
        // asynchronously retrieve the document
        ApiFuture<QuerySnapshot> future = firebase.getFirestore().collection("productos").whereEqualTo("name", name).get();

        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return docData;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return docData;
        }

        for (DocumentSnapshot document : documents) {

            docData.add(document.toObject(Product.class));
        }
        return docData;
    }

    @Override
    public StoredProductResult setProduct(List<Product> products) {
        StoredProductResult storedProductResponse = new StoredProductResult();
        for (Product product : products) {

            Map<String, Object> docData = new HashMap<>();
            docData.put("name",product.getName());
            docData.put("stock",product.getStock());
            docData.put("category",product.getCategory());
            docData.put("brand",product.getBrand());
            docData.put("price",product.getPrice());

            if(!checkIfProductExist(product.getName(),product.getBrand())){
                CollectionReference posts = firebase.getFirestore().collection("productos");
                ApiFuture<WriteResult> writeResultApiFuture = posts.document().create(docData);
                try {
                    if(writeResultApiFuture.get() != null){
                        storedProductResponse.addProductAdded(new StoredProductResponse(
                                product.getName(),
                                product.getBrand(),
                                null
                        ));
                    }
                } catch (InterruptedException e) {
                    storedProductResponse.addProductsNotAdded(new StoredProductResponse(
                            product.getName(),
                            product.getBrand(),
                            "Exception"
                    ));

                } catch (ExecutionException e) {
                    storedProductResponse.addProductsNotAdded(new StoredProductResponse(
                            product.getName(),
                            product.getBrand(),
                            "Exception"
                    ));
                }

            }else{
                storedProductResponse.addProductsNotAdded(new StoredProductResponse(
                        product.getName(),
                        product.getBrand(),
                        "Already in stock"
                ));
            }
        }
        return storedProductResponse;
    }

    private boolean checkIfProductExist(String name,String brand){

        ApiFuture<QuerySnapshot> future = firebase.getFirestore().collection("productos")
                .whereEqualTo("name", name)
                .whereEqualTo("brand",brand)
                .get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(documents.isEmpty()){
            return false;
        }
        return true;
    }


}
