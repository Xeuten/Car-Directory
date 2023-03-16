package com.example.CarDirectory.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    @Value("${messages.incorrect_URL}")
    private String incorrectURL;

    public String errorResponse() {
        throw new RuntimeException(incorrectURL);
    }

}
