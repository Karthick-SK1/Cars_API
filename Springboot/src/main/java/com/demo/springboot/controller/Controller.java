package com.demo.springboot.controller;

import com.demo.springboot.services.CarServices_HASHMAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class Controller {

    @GetMapping("/allcars")
    public String getallcars(@RequestParam(value = "page")int page ,@RequestParam(value = "limit")int limit){

        return "Get All cars is triggered for "+page+" and for the limit is "+ limit; //QUERY PARAMETER
    }

    @PostMapping
    public String createcars(){
        return "Create car is triggered";
    }


    @DeleteMapping
    public String deleteCard(){

        return "Car is Deleted";
    }

    @GetMapping(path = "/{id}")
    public String getcarbyid(@PathVariable String id){  //PATH PARAMETER

        return  "Get car by id "+ id;

    }


}
