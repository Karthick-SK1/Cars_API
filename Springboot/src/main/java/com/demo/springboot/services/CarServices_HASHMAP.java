package com.demo.springboot.services;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.beans.Responses;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class CarServices_HASHMAP {

    private static HashMap<Integer, Car_bean> carmap;

    public CarServices_HASHMAP() {

        carmap = new HashMap<Integer, Car_bean>();

        Car_bean indiancars = new Car_bean(1, "Hyundai", "1200000", "i20");
        carmap.put(1, indiancars);

        Car_bean germancars = new Car_bean(2, "Volkswagen", "1800000", "Polo GT");
        carmap.put(2, germancars);

        Car_bean japancars = new Car_bean(3, "Kia", "184500", "Sonnet");
        carmap.put(3, japancars);
    }

    public List<Car_bean> getAllCars() {
        List<Car_bean> cars = new ArrayList<>(carmap.values());
        return cars;
    }

    public Car_bean getCarById(int id) {
        return carmap.get(id);
    }

    public Car_bean getCarByBrand(String brand) {
        Car_bean car = null;
        for (int i : carmap.keySet()) {
            if (carmap.get(i).getBrand().equals(brand)) {
                car = carmap.get(i);
            }
        }
        return car;
    }

    public Car_bean addCar(Car_bean car) {
        car.setId(getmaxPrice());
        carmap.put(car.getId(), car);

        Responses res = new Responses();
        res.setMessage("Car Added Successfully");
        res.setId(car.getId());

        return car;
    }


    public int getmaxPrice() {
        int max = 0;
        for (int id : carmap.keySet()) {
            if (max <= id) {
                max = id;
            }
        }

        return max + 1;
    }

    public Responses updatecarPrice(Car_bean carBean) {
        Responses res = new Responses();
        if (carBean.getId() > 0) {
            carmap.put(carBean.getId(), carBean);
            res.setMessage("Car Price is Updated Successfully");
            res.setId(carBean.getId()); // Set the updated car's ID to the Responses object
        } else {
            res.setMessage("Car ID is Invalid");
        }
        return res;
    }

    public Responses deletecar(int id) {
        Responses res = new Responses();
        if (carmap.containsKey(id)) {
            carmap.remove(id);
            res.setMessage("Car Deleted");
            res.setId(id); // Set the deleted car's ID to the Responses object
        } else {
            res.setMessage("Invalid Car ID");
        }
        return res;
    }


}