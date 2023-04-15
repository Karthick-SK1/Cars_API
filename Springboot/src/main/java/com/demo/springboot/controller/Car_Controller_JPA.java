package com.demo.springboot.controller;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.beans.Responses;
import com.demo.springboot.services.Car_Services_JPA;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class Car_Controller_JPA {

    @Autowired
    Car_Services_JPA carServicesJpa;

    @GetMapping("/Cars")
    public List<Car_bean> getallcars() {

        return carServicesJpa.getAllCars();
    }

    @GetMapping("/Cars/{id}")

    public ResponseEntity<Car_bean> getcarByid(@PathVariable(value = "id") int id) {
        try {
            Car_bean carBean = carServicesJpa.getcarByid(id);
            return new ResponseEntity<Car_bean>(carBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Cars/brand")
    public ResponseEntity<Car_bean> getCarbyBrand(@RequestParam(value = "brand") String brand) {
        try {
            Car_bean carBean = carServicesJpa.getcarbybrand(brand);
            return new ResponseEntity<Car_bean>(carBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/AddCar")
    @ResponseStatus(HttpStatus.CREATED)
    public Car_bean addCar(@RequestBody Car_bean carBean) {
        return carServicesJpa.addcar(carBean);
    }


    @PutMapping("/updateCarprice/{id}")
    public ResponseEntity<Car_bean> updateCarPrice(@PathVariable(value = "id") int id, @RequestBody Car_bean carBean) {
        try {

            Car_bean existcar = carServicesJpa.getcarByid(id);

            existcar.setModel(carBean.getModel());

            Car_bean carBean1 = carServicesJpa.updateCarPrice(existcar);

            return new ResponseEntity<Car_bean>(carBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Car_bean> deleteCar(@PathVariable(value = "id") int id) {
           Car_bean carBean2 = null;
        try {

            Car_bean existcar = carServicesJpa.getcarByid(id);

            carServicesJpa.deleteCar(carBean2);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Car_bean>(carBean2,HttpStatus.OK);
    }


}




