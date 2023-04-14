package com.demo.springboot.services;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.beans.Responses;
import com.demo.springboot.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class Car_Services_JPA {

    @Autowired
    Repository repository;

    public List<Car_bean> getAllCars() {

        return repository.findAll();

    }

    public Car_bean getcarByid(int id) {

        List<Car_bean> carBeans = repository.findAll();
        Car_bean carBean = null;

        for (Car_bean con :carBeans) {

            if (con.getId()==id)
                carBean = con;
        }
        return carBean;
    }

    public Car_bean getcarbybrand(String brand) {

        List<Car_bean> brands = repository.findAll();

        Car_bean carBean = null;

        for (Car_bean car : brands) {

            if (car.getBrand().equalsIgnoreCase(brand)) {
                carBean = car;
            }
        }

        return carBean;
    }


    public Car_bean addcar(Car_bean carBean) {

        carBean.setId(getmaxPrice());
        repository.save(carBean);
        return carBean;
    }

    public int getmaxPrice() {

        return repository.findAll().size() + 1;
    }


    public Car_bean updateCarPrice(Car_bean carBean) {

        repository.save(carBean);
        return carBean;
    }

    public Responses deleteCar(Car_bean id) {

        Responses res = new Responses();
        res.setMessage("Car is Deleted Successfully");
        return res;
    }




}
