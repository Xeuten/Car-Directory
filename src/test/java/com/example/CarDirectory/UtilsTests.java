package com.example.CarDirectory;

import com.example.CarDirectory.util.Utils;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTests {

    private Utils utils = new Utils();

    @Test
    public void validateCorrectCriteria() {
        assertTrue(utils.validateCriteria(new String[]{"номер: 1204", "цвет: чёрный"}));
    }

    @Test
    public void validateTwoSameCriteria() {
        assertFalse(utils.validateCriteria(new String[]{"номер: 1300", "номер: 1245"}));
    }

    @Test
    public void validateNonExistentCriteria() {
        assertFalse(utils.validateCriteria(new String[]{"номе: 1200"}));
    }

    @Test
    public void validateIncorrectFormatCriteria() {
        assertFalse(utils.validateCriteria(new String[]{"цвет: ч:рный"}));
    }

    @Test
    public void validateCorrectRawData() {
        assertTrue(utils.validateRawData("something=something"));
    }

    @Test
    public void validateIncorrectRawData() {
        assertFalse(utils.validateRawData("something=something=something"));
    }

    @Test
    @SneakyThrows
    public void ensureThatJsonIsValid() {
        assertDoesNotThrow(() -> new JSONObject(utils.toPrettyJSON(Map.of("\"Количество записей\"", 0))));
    }

    @Test
    @SneakyThrows
    public void ensureThatJsonIsInvalid() {
        assertThrows(JSONException.class, () -> {
            new JSONObject(utils.toPrettyJSON(Map.of("Количество записей", 0)));
        });
    }

    @Test
    public void correctTranslation() {
        assertEquals("color", utils.rusToEng("цвет"));
    }

}
