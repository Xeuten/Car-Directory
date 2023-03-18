package com.example.CarDirectory.model;

import com.google.gson.GsonBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Этот класс используется для объектно-реляционного отображения объекта класса Car в
 * базу данных. У этого класса есть следующие поля: номер, марка, цвет, год выпуска,
 * и дата создания записи.
 */
@Data
@Entity
@Table(name="cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    public Car(CarWithoutDate car) {
        this.registration_id = car.registration_id;
        this.vehicle_brand = car.vehicle_brand;
        this.color = car.color;
        this.year_of_manufacture = car.year_of_manufacture;
        this.creation_date = OffsetDateTime.now().toString();
    }

    @Id
    @Column
    public String registration_id;

    @Column
    public String vehicle_brand;

    @Column
    public String color;

    @Column
    public String year_of_manufacture;

    @Column
    private String creation_date;

    public String toJSONString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
