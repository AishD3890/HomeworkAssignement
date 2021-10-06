package com.aishu.homework.models;

import org.springframework.stereotype.Component;

@Component
public class Inventory {
    public String identificationNumber;
    public String name;
    public int stock;

    @Override
    public String toString() {
        return "articles{" +
                "identificationNumber='" + identificationNumber + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                '}';
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
