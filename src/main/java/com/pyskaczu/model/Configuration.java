package com.pyskaczu.model;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class Configuration {
    private static final String DEFAULT_WORKBOOK_FILE_PATH = "Budget.xlsm";
    private static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";

    public String workbookFileLocation = DEFAULT_WORKBOOK_FILE_PATH;
    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    private Configuration(){}
}
