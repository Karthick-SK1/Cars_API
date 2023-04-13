package com.demo.springboot.beans;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Car_bean")
public class Car_bean {


    @Id
    @Column(name = "id")
    int id;

    @Column(name = "brand")
    String brand;

    @Column(name = "price")
    String price;

    @Column(name = "model")
    String model;

    public Car_bean(int id, String brand, String price, String model) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.model = model;
    }

    public Car_bean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
