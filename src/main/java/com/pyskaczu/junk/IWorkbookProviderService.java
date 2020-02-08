package com.pyskaczu.junk;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public interface IWorkbookProviderService {
    Workbook getWorkbook() throws IOException, InvalidFormatException;
    String getWorkbookPath();
    void setWorkbookPath(String workbookPath);
}
