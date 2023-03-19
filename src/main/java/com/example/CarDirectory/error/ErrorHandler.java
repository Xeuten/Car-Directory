package com.example.CarDirectory.error;

import com.example.CarDirectory.util.Utils;
import com.google.gson.JsonSyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * В этом классе перечислены обработчики, которые вызываются при возникновении
 * любых ошибок.
 */
@ControllerAdvice
@Log4j2
public class ErrorHandler {

    @Autowired
    private Utils utils;

    @Value("${messages.car_exists}")
    private String carExists;

    @Value("${messages.car_not_exists}")
    private String carNotExists;

    @Value("${messages.unknown_error}")
    private String unknownError;

    @Value("{messages.incorrect_method}")
    private String incorrectMethod;

    @Value("${messages.denied}")
    private String denied;

    @Value("${messages.incorrect_JSON}")
    private String incorrectJSON;

    @Value("${messages.incorrect_URL}")
    private String incorrectURL;

    @Value("${messages.incorrect_filters}")
    private String incorrectFilters;

    @Value("${logging.messages.add_car}")
    private String addCar;

    /**
     * Этот обработчик вызывается при некорректных пользовательских вводах и также в
     * случае, если запрос выдал альтернативный результат (отсутствие нужной записи в
     * базе при удалении или наличие при добавлении).
     * @param e Исключение, выброшенное методом.
     * @return Сообщение о соответствующей ошибке.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        String generifiedString = e.getMessage().replaceAll("\"[^\"]*\"", "\"%s\"");
        if(generifiedString.equals(carExists)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        if(generifiedString.equals(carNotExists)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        if(e.getMessage().equals(denied)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(denied);
        }
        if(e.getMessage().equals(incorrectURL)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(incorrectURL);
        }
        if(e.getMessage().equals(incorrectFilters)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectFilters);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(unknownError);
    }

    /**
     * Этот обработчик вызывается в случае, если пользователь попытался обратиться к
     * URL'у, соответствующему POST запросу с помощью GET запроса.
     * @return Сообщение о соответствующей ошибке.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectMethod);
    }

    /**
     * Этот обработчик вызывается в случае, если ввод пользователя не соответствует
     * JSON'у корректного формата.
     * @return Сообщение о соответствующей ошибке.
     */
    @ExceptionHandler(JsonSyntaxException.class)
    public ResponseEntity<String> handleJsonSyntax() {
        log.warn(addCar + incorrectJSON);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectJSON);
    }

}
