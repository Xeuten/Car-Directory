package com.example.CarDirectory.services;

import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    public ResponseEntity<String> statisticsResponse() {
        HashMap<String, Object> outputMap = new HashMap<>();
        long numberOfEntries = carRepository.count();
        outputMap.put("Количество записей", numberOfEntries);
        if(numberOfEntries == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(utils.toPrettyJSON(outputMap));
        }
        List<OffsetDateTime> dates = carRepository.selectDates().stream().map(OffsetDateTime::parse).toList();
        outputMap.put("Дата первой добавленной записи", dates.stream().min(OffsetDateTime::compareTo).get());
        outputMap.put("Дата последней добавленной записи", dates.stream().max(OffsetDateTime::compareTo).get());
        return ResponseEntity.status(HttpStatus.OK).body(utils.toPrettyJSON(outputMap));
    }

}
