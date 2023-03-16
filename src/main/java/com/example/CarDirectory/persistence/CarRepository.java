package com.example.CarDirectory.persistence;

import com.example.CarDirectory.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    @Query(value = "SELECT creation_date FROM cars", nativeQuery = true)
    List<String> selectDates();

}
