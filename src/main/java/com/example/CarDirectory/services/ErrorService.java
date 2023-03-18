package com.example.CarDirectory.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    @Value("${messages.incorrect_URL}")
    private String incorrectURL;

    /**
     * Этот метод вызывается в случае, если пользователь обратился к некорректному URL'у.
     * В нём всегда выбрасывается исключение.
     */
    public String errorResponse() {
        throw new RuntimeException(incorrectURL);
    }

}
