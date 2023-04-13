package com.demo.springboot.repositories;

import com.demo.springboot.beans.Car_bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Car_bean,Integer> {


}
