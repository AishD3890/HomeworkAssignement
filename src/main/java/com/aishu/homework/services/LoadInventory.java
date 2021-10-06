package com.aishu.homework.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aishu.homework.models.Inventory;
import com.aishu.homework.models.Warehouse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadInventory {

    @Autowired
    Warehouse warehouse;

    private static final org.slf4j.Logger LoadArticlesServiceLogger = LoggerFactory.getLogger(LoadInventory.class);


    public List<Inventory> load(String inventoryData) throws JsonProcessingException {

        JsonNode inventoryNode = new ObjectMapper().readTree(inventoryData);
        JsonNode inventoryArrayNode = inventoryNode.get("inventory");
       List<Inventory> existingArticles =  warehouse.getArticle();
        List<Inventory> newArticles =  new ArrayList<Inventory>() ;
        if (inventoryArrayNode.isArray() && null!=  existingArticles) {
            for (JsonNode jsonNode : inventoryArrayNode) {
                for (Inventory existingArticle : existingArticles) {
                    System.out.println("For every Article node in Inventory File");
                   if(existingArticle.getIdentificationNumber().equalsIgnoreCase(jsonNode.get("art_id").asText()))
                   {
                       existingArticle.setStock(Integer.parseInt(jsonNode.get("stock").asText()));
                   }
                   else
                   {
                       Inventory article = new Inventory();
                       article.setIdentificationNumber(jsonNode.get("art_id").asText());
                       article.setName(jsonNode.get("name").asText());
                       article.setStock(Integer.parseInt(jsonNode.get("stock").asText()));
                       existingArticles.add(article);
                   }
                }

            }
            warehouse.setArticle(existingArticles);
        }
        else
        {
            for (JsonNode jsonNode : inventoryArrayNode) {
                Inventory article = new Inventory();
                article.setIdentificationNumber(jsonNode.get("art_id").asText());
                article.setName(jsonNode.get("name").asText());
                article.setStock(Integer.parseInt(jsonNode.get("stock").asText()));
                newArticles.add(article);
            }
            warehouse.setArticle(newArticles);
        }

return warehouse.getArticle();
}
    }
