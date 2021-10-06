package com.aishu.homework.services;

import com.aishu.homework.models.ContainedArticles;
import com.aishu.homework.models.Products;
import com.aishu.homework.models.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadProducts {

    @Autowired
    Store store;
    private static final org.slf4j.Logger LoadProductsServiceLogger = LoggerFactory.getLogger(LoadProducts.class);


    public List<Products> load(String productData) throws JsonProcessingException {

        JsonNode productNode = new ObjectMapper().readTree(productData);
        JsonNode productArrayNode = productNode.get("products");


        List<Products> existingProducts =  store.getProductCollection();
        List<Products> newProducts =  new ArrayList<Products>() ;
        if (productArrayNode.isArray() && null!=  existingProducts) {
            for (JsonNode jsonNode : productArrayNode) {
                for (Products existingProduct : existingProducts) {

                   if(existingProduct.getName().equalsIgnoreCase(jsonNode.get("name").asText()))
                   {
                       System.out.println("Product Already Exists");
                   }
                   else
                   {
                       Products product = new Products();
                       product.setName(jsonNode.get("name").asText());
                       JsonNode containedArticlesArrayNode = jsonNode.get("contain_articles");
                       List<ContainedArticles> subProducts = new ArrayList<ContainedArticles>();
                       for(JsonNode article : containedArticlesArrayNode )
                       {
                           ContainedArticles containedArticle = new ContainedArticles();

                           containedArticle.setIdentificationNumber(article.get("art_id").asText());
                           containedArticle.setAmount_of(Integer.parseInt(article.get("amount_of").asText()));
                           subProducts.add(containedArticle);

                       }
                       product.setContainedArticles(subProducts);
                       existingProducts.add(product);
                   }
                }

            }
            store.setProductCollection(existingProducts);
        }
        else
        {
            for (JsonNode jsonNode : productArrayNode) {
                Products product = new Products();
                product.setName(jsonNode.get("name").asText());
                JsonNode containedArticlesArrayNode = jsonNode.get("contain_articles");
                List<ContainedArticles> subProducts = new ArrayList<ContainedArticles>();
                for(JsonNode article : containedArticlesArrayNode )
                {
                    ContainedArticles containedArticle = new ContainedArticles();

                    containedArticle.setIdentificationNumber(article.get("art_id").asText());
                    containedArticle.setAmount_of(Integer.parseInt(article.get("amount_of").asText()));
                    subProducts.add(containedArticle);

                }
                product.setContainedArticles(subProducts);
                newProducts.add(product);
            }
            store.setProductCollection(newProducts);
        }

return store.getProductCollection();
}
    }
