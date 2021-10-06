package com.aishu.homework.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Warehouse {
    List<Inventory> articleInventory;

    public List<Inventory> getArticle() {
        return articleInventory;
    }

    public void setArticle(List articles) {
        this.articleInventory = articles;
    }
}
