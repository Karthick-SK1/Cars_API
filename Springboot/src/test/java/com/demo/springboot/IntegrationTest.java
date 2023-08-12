package com.demo.springboot;

import com.demo.springboot.beans.Car_bean;
import org.json.JSONException;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.random.RandomGenerator;


@SpringBootTest(classes = IntegrationTest.class)
public class IntegrationTest {

    public Car_bean car;

    @Test(priority = 1)
    public void Test_getallcars() throws FileNotFoundException, JSONException {

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/Cars", String.class);

        System.out.println(response.getStatusCode());

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK, "Get All Cars Response is not 200");
    }


    @Test(priority = 2)
    public void Test_getcarbyid() throws JSONException {

        String expected = "{\n" +
                "    \"id\": 5,\n" +
                "    \"brand\": \"Audi\",\n" +
                "    \"price\": \"1.20 C\",\n" +
                "    \"model\": \"R8\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/Cars/5", String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test(priority = 3)
    public void Test_CarbyBrand() throws JSONException {

        String expected = "{\n" +
                "    \"id\": 3,\n" +
                "    \"brand\": \"Tata\",\n" +
                "    \"price\": \"10,00,000\",\n" +
                "    \"model\": \"Altroz\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090//Cars/brand?brand=Tata", String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test(priority = 4)
    public void Test_addCar() throws JSONException {

        String expected = "{\n" +
                "    \"id\": 6,\n" +
                "    \"brand\": \"Porsche\",\n" +
                "    \"price\": \"3 C\",\n" +
                "    \"model\": \"Taycan\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        Random rand = new Random();

        int max = 0;
        int min = 0;
        int randomNum = rand.nextInt((max - min) + 1) + min;

        Car_bean car = new Car_bean(randomNum, "Porsche", "3 C", "Taycan");

        HttpEntity<Car_bean> request = new HttpEntity<>(car, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8090/AddCar", request, String.class);

        Assert.assertTrue(response.getBody().contains("Taycan"));

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
