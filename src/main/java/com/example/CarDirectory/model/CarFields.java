package com.example.CarDirectory.model;

public enum CarFields {
    номер("номер"),
    марка("марка"),
    цвет("цвет"),
    год_выпуска("год выпуска"),
    дата_создания("дата создания");

    private String field;

    CarFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
