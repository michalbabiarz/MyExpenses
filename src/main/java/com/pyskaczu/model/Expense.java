package com.pyskaczu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Expense {
    private final LocalDate date;
    private final float ammount;
    private final String category;
    private final String subcategory;
    private final String type;
    private final String comment;
}
