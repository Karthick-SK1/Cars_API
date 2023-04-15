package com.demo.springboot;

import com.demo.springboot.beans.Car_bean;
import com.demo.springboot.controller.Car_Controller_JPA;
import com.demo.springboot.services.Car_Services_JPA;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ComponentScan(basePackages = "com.demo.springboot")
@SpringBootTest(classes = ControllerTest.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private Car_Services_JPA services;

    @InjectMocks
    private Car_Controller_JPA controller;

    public List<Car_bean> mycars;

    Car_bean car;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test(priority = 1)
    public void Test_getAllcars() throws Exception {
        mycars = new ArrayList<Car_bean>();
        mycars.add(new Car_bean(1,"Lamborghini","2.35 C","Urus"));
        mycars.add(new Car_bean(2,"Audi","1.20 C","R8"));
        mycars.add(new Car_bean(3,"BMW","4.35 C","M8"));
        when(services.getAllCars()).thenReturn(mycars);
        this.mockMvc.perform(get("/Cars")).andExpect(status().isOk()).andDo(print());
    }
    @Test(priority = 2)
    public void Test_getcarbyid() throws Exception {

        car = new Car_bean(2,"Audi","1.20 C","R8");

        int carid = 1;

        when(services.getcarByid(carid)).thenReturn(car);

        this.mockMvc.perform(get("/Cars/{id}",carid))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("R8"))
                .andDo(print());
    }
    @Test(priority = 3)
    public void Test_getcarbyBrand() throws Exception {

        car = new Car_bean(1,"BMW","75 L","GT 730");

        String brand = "BMW";

        when(services.getcarbybrand(brand)).thenReturn(car);

        this.mockMvc.perform(get("/Cars/brand").param("brand",brand)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(brand))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("75 L"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("GT 730"))
                .andDo(print());
    }
    @Test(priority = 4)
    public  void Test_addCar() throws Exception {


        car = new Car_bean(1, "LAND ROVER", "4 C", "Range Rover - Sport");

        when(services.addcar(car)).thenReturn(car);

        ObjectMapper obj = new ObjectMapper();

        String jsonbody = obj.writeValueAsString(car);

        this.mockMvc.perform(post("/AddCar", jsonbody).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test(priority = 5)
    public void Test_UpdatecarPrice() throws Exception {

        car = new Car_bean(1, "LAND ROVER", "3.5 C", "Range Rover - Sport");

        int carid = 1;

        when(services.getcarByid(carid)).thenReturn(car);
        when(services.updateCarPrice(car)).thenReturn(car);

        ObjectMapper obj = new ObjectMapper();

        String JsonBody = obj.writeValueAsString(car);

        this.mockMvc.perform(put("/updateCarprice/{id}",carid).content(JsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("3.5 C"))
                .andDo(print());

    }
    @Test(priority = 6)
    public void Test_Deletecar() throws Exception {

        car = new Car_bean(1, "LAND ROVER", "3.5 C", "Range Rover - Sport");

        int carid = 1;
        when(services.getcarByid(carid)).thenReturn(car);

        this.mockMvc.perform(delete("/deleteCar/{id}",carid))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
