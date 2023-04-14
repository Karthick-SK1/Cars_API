package com.demo.springboot;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.repositories.Repository;
import com.demo.springboot.services.Car_Services_JPA;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = servicesTest.class)
public class servicesTest {

    @Mock
    private Repository repository;

    @InjectMocks
    private Car_Services_JPA services;

    @BeforeClass
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void Test_getcars() {

        List<Car_bean> mycars = new ArrayList<Car_bean>();

        mycars.add(new Car_bean(1, "Toyota", "11,00,000", "Glanza"));
        mycars.add(new Car_bean(2, "Mahindra", "09,00,000", "TUV300"));
        mycars.add(new Car_bean(3, "Suzuki", "08,00,000", "Baleno"));

        when(repository.findAll()).thenReturn(mycars);
        Assert.assertEquals(3, services.getAllCars().size());
    }


    @Test
    public void Test_getcarbyiD() {
        List<Car_bean> mycars = new ArrayList<Car_bean>();

        mycars.add(new Car_bean(1, "Toyota", "11,00,000", "Glanza"));
        mycars.add(new Car_bean(2, "Mahindra", "09,00,000", "TUV300"));
        mycars.add(new Car_bean(3, "Suzuki", "08,00,000", "Baleno"));

        int carid = 1;
        when(repository.findAll()).thenReturn(mycars);
        int ids = services.getcarByid(carid).getId();
        Assert.assertEquals(carid,ids);
    }

    @Test
    public void Test_getcarbyName() {
        List<Car_bean> mycars = new ArrayList<Car_bean>();

        mycars.add(new Car_bean(1, "Toyota", "11,00,000", "Glanza"));
        mycars.add(new Car_bean(2, "Mahindra", "09,00,000", "TUV300"));
        mycars.add(new Car_bean(3, "Suzuki", "08,00,000", "Baleno"));

        String brand = "Suzuki";
        when(repository.findAll()).thenReturn(mycars);
        String brands = services.getcarbybrand(brand).getBrand();
        Assert.assertEquals(brand,brands);
    }

    @Test
    public void Test_Addcar(){

        Car_bean car = new Car_bean(6, "Citreon", "12,00,000", "CX700");

        when(repository.save(car)).thenReturn(car);

        Assert.assertEquals(car,services.addcar(car));
    }

    @Test
    public void Test_UpdateCarPrice(){

        Car_bean car = new Car_bean(6, "Citreon", "12,40,000", "CX700");
        when(repository.save(car)).thenReturn(car);

        Assert.assertEquals(car,services.updateCarPrice(car));

    }
    @Test
    public void Test_DeleteCar(){

        Car_bean car = new Car_bean(6, "Citreon", "12,40,000", "CX700");
        services.deleteCar(car);
        verify(repository,times(1)).delete(car);
    }
}
