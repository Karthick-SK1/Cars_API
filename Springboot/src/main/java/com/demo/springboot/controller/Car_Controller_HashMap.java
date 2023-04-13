package com.demo.springboot.controller;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.beans.Responses;
import com.demo.springboot.services.CarServices_HASHMAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Car_Controller_HashMap {

    @Autowired

    CarServices_HASHMAP carservices;

 @GetMapping("/getcars")
    public List<Car_bean> getAllCars(){

     return carservices.getAllCars();

    }

    @GetMapping("/getcars/{id}")
    public Car_bean getCarbyid(@PathVariable(value = "id")int id){
     return carservices.getCarById(id);
    }

    @GetMapping("/getcars/carbrand")
    public Car_bean getcarbyBrand(@RequestParam(value = "brand")String brand){

     return carservices.getCarByBrand(brand);

    }

    @PostMapping("/addCar")
    public Car_bean addCar(@RequestBody Car_bean carBean){

     return  carservices.addCar(carBean);

    }

    @PutMapping("/updatecarPrice")
    public Responses updateCarPrice(@RequestBody Car_bean carBean){

     return carservices.updatecarPrice(carBean);

    }

    @DeleteMapping("/deletecar/{id}")
    public Responses deleteCar(@PathVariable(value = "id")int id){

     return carservices.deletecar(id);
    }
}
