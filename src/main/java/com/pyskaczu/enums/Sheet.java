package com.pyskaczu.enums;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum Sheet {
    CURRENT_MONTH(LocalDate.now().format(DateTimeFormatter.ofPattern("MM.yyyy"))),
    SETTINGS("Ustawienia");

    @Getter
    private String name;

    private Sheet(String name) {
        this.name = name;
    }
}
