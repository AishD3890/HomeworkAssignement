package com.aishu.homework.controller;



import com.aishu.homework.models.Products;
import com.aishu.homework.services.LoadInventory;
import com.aishu.homework.services.LoadProducts;
import com.aishu.homework.services.ShowProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.aishu.homework.models.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class WarehouseController {

    private static final Logger WarehouseControllerLogger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    LoadInventory loadInventory;

    @Autowired
    LoadProducts loadproducts;

    @Autowired
    ShowProduct showproduct;

    @Autowired
    private Environment env;




    @PostMapping(value = "/loadInventory" ,produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public List<Inventory> loadInventory(@RequestBody String inventoryData) throws JsonProcessingException {
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        WarehouseControllerLogger.info("Logging File Details --- {}", inventoryData.toString());
        inventoryList = loadInventory.load(inventoryData);
        return inventoryList;

    }

    @PostMapping(value = "/loadProducts" ,produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public List<Products> loadProducts(@RequestBody String productData) throws JsonProcessingException {
        List<Products> productList = new ArrayList<Products>();
        WarehouseControllerLogger.info("Logging File Details --- {}", productData.toString());
        productList = loadproducts.load(productData);
        return productList;

    }

    @GetMapping(value= "/getProducts")
    public HashMap<String,Integer> getAll() {
        return showproduct.getAllwithInventory();
    }
}