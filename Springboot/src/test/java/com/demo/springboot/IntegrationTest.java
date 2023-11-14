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


@SpringBootTest(classes = IntegrationTest.class)
public class IntegrationTest {

    public Car_bean car;

    @Test(priority = 1)
    public void Test_getallcars() throws FileNotFoundException, JSONException {
//        String file = "C:\\Users\\karthick.sha\\IdeaProjects\\Java\\Springboot\\src\\allcars.json";
//        FileReader reader = new FileReader(file);

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"brand\": \"Hyundai\",\n" +
                "        \"price\": \"12,00,000\",\n" +
                "        \"model\": \"i20\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"brand\": \"Volkswagen\",\n" +
                "        \"price\": \"14,00,000\",\n" +
                "        \"model\": \"Polo GT\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"brand\": \"Tata\",\n" +
                "        \"price\": \"10,00,000\",\n" +
                "        \"model\": \"Altroz\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"brand\": \"Suzuki\",\n" +
                "        \"price\": \"09,00,000\",\n" +
                "        \"model\": \"Swift\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"brand\": \"Audi\",\n" +
                "        \"price\": \"1.20 C\",\n" +
                "        \"model\": \"R8\"\n" +
                "    }\n" +
                "]";

        TestRestTemplate restTemplate = new TestRestTemplate();

      ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/Cars",String.class);

        System.out.println(response.getStatusCode());

        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected,response.getBody(),false);
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

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/Cars/5", String.class);

        JSONAssert.assertEquals(expected,response.getBody(),false);
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

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080//Cars/brand?brand=Tata", String.class);

        JSONAssert.assertEquals(expected,response.getBody(),false);
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

        Car_bean car = new Car_bean(6,"Porsche","3 C","Taycan");

        HttpEntity<Car_bean> request = new HttpEntity<>(car, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/AddCar", request, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }



}
