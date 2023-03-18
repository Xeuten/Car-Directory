package com.example.CarDirectory.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LogService {

    @Value("${messages.no_logs}")
    private String noLogs;

    /**
     * Этот метод возвращает содержимое файла логов. Если файл пуст, то возвращается
     * сообщение об этом.
     * @param model
     * @return Строка, соответствующая шаблону, заполненному содержимым файла логов
     * или сообщением, что файл пуст.
     */
    @SneakyThrows
    public String logResponse(Model model) {
        String logs = String.join("",
                Files.readAllLines(Paths.get("logs/Car-Directory.log")).toArray(String[]::new));
        model.addAttribute("message", logs.isEmpty() ? noLogs : logs);
        return "template1";
    }

}
