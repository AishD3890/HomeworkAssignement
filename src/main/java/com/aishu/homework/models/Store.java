package com.aishu.homework.models;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Store {
    List<Products> productCollection;

    public List<Products> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(List<Products> productCollection) {
        this.productCollection = productCollection;
    }
}
