package com.aishu.homework.services;

import com.aishu.homework.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class ShowProduct {

@Autowired
Store store;

@Autowired
Warehouse warehouse;

    public HashMap<String,Integer> getAllwithInventory() {

        List<Products> existingProducts = store.getProductCollection();
        HashMap<String,Integer> existingProductswithInventory = new HashMap<String,Integer>();
        List<Inventory> inventoryData= warehouse.getArticle();
        HashMap<String,Integer> productStock = new HashMap<String,Integer>();
        for(Products product:existingProducts)
        {
            for(ContainedArticles containedArticle : product.getContainedArticles())
            {
               for(Inventory inventoryDetails: inventoryData)
               {
                   if(inventoryDetails.getIdentificationNumber().equalsIgnoreCase(containedArticle.getIdentificationNumber()))
                      if(inventoryDetails.getStock()>1)
                       productStock.put(containedArticle.getIdentificationNumber(),inventoryDetails.getStock());

               }



            }
            int minValueInMap=(Collections.min(productStock.values()));
            existingProductswithInventory.put(product.getName(),minValueInMap);
            minValueInMap = 0;//resetting minimum inventory
            productStock.clear();

        }
        return existingProductswithInventory;
    }
}
