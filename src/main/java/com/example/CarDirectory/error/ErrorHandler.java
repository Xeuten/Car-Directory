package com.example.CarDirectory.error;

import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(unknownError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectMethod);
    }

    @ExceptionHandler(JsonSyntaxException.class)
    public ResponseEntity<String> handleJsonSyntax() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectJSON);
    }

}
