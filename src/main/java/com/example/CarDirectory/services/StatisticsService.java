package com.example.CarDirectory.services;

import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class StatisticsService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    @Value("${logging.messages.get_statistics}")
    private String getStatistics;

    /**
     * Этот метод возвращает статистику о содержимом справочника, а именно количество
     * записей и даты первой и последней добавленных записей.
     * @return Строка, соответствующая шаблону, заполненному статистикой базы.
     */
    public String statisticsResponse(Model model) {
        HashMap<String, Object> outputMap = new HashMap<>();
        long numberOfEntries = carRepository.count();
        outputMap.put("Количество записей", numberOfEntries);
        if(numberOfEntries == 0) {
            model.addAttribute("message", utils.toPrettyJSON(outputMap));
        } else {
            List<OffsetDateTime> dates = carRepository.findAll().stream()
                    .map(x -> OffsetDateTime.parse(x.getCreation_date()))
                    .toList();
            outputMap.put("Дата первой добавленной записи", dates.stream().min(OffsetDateTime::compareTo).get());
            outputMap.put("Дата последней добавленной записи", dates.stream().max(OffsetDateTime::compareTo).get());
            model.addAttribute("message", utils.toPrettyJSON(outputMap));
        }
        log.info(getStatistics);
        return "template1";
    }

}
