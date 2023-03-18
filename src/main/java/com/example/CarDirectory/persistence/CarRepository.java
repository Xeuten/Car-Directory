package com.example.CarDirectory.persistence;

import com.example.CarDirectory.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String>, CarRepositoryCustom {}
