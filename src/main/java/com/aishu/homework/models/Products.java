package com.aishu.homework.models;

import java.util.List;

public class Products {
    String name;
    List<ContainedArticles> containedArticles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContainedArticles> getContainedArticles() {
        return containedArticles;
    }

    public void setContainedArticles(List<ContainedArticles> containedArticles) {
        this.containedArticles = containedArticles;
    }
}
