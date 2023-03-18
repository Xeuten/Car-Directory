package com.example.CarDirectory.services;

import com.example.CarDirectory.model.Car;
import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class GetCarsListService {

    private static final Logger log = LogManager.getLogger(GetCarsListService.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    @Value("${messages.incorrect_filters}")
    private String incorrectFilters;

    @Value("${messages.no_items}")
    private String noItems;

    @Value("${messages.denied}")
    private String denied;

    @Value("${logging.messages.get_cars_list}")
    private String getCarsList;

    /**
     * Этот метод находит в базе записи, которые соответствуют фильтрам, переданным
     * пользователем. Если ввод некорректен, то метод выбрасывает соответствующее
     * исключение.
     * @param rawFilters "Сырые" фильтры - то есть фильтры с префиксом, подлежащим
     * удалению.
     * @param model Модель, которая будет передана Представлению.
     * @return Строка, соответствующая шаблону, заполненному записями, соответствующими
     * фильтрам.
     */
    public String getCarsListResponse(String rawFilters, Model model) {
        if(!utils.validateRawData(rawFilters)) {
            log.warn(getCarsList + denied);
            throw new RuntimeException(denied);
        }
        HashMap<String, String> criteria = new HashMap<>();
        String filters = rawFilters.split("=")[1];
        if(filters.indexOf(':') != -1) {
            String[] criteriaArr = filters.split(",");
            if(!utils.validateCriteria(criteriaArr)){
                log.warn(getCarsList + incorrectFilters);
                throw new RuntimeException(incorrectFilters);
            }
            Arrays.stream(criteriaArr).forEach(x -> {
                String[] entry = x.split(":");
                criteria.put(utils.rusToEng(entry[0].trim().toLowerCase()), entry[1].trim());
            });
        }
        List<Car> cars = !criteria.isEmpty() ? carRepository.findCarsByCriteria(criteria) : carRepository.findAll();
        if(!cars.isEmpty()) {
            StringBuilder builder = new StringBuilder("{\n");
            cars.forEach(x -> builder.append(x.toJSONString()).append(",\n"));
            builder.deleteCharAt(builder.lastIndexOf(",")).append('}');
            log.info(getCarsList + "успешно");
            model.addAttribute("message", builder.toString());
        } else {
            log.info(getCarsList + noItems);
            model.addAttribute("message", noItems);
        }
        return "template1";
    }

}
