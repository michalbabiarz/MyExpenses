package com.pyskaczu.junk;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Paths;

public class WorkbookProviderService implements  IWorkbookProviderService{
    private String workbookPath;

    @Override
    public Workbook getWorkbook() throws IOException, InvalidFormatException {
        return new XSSFWorkbook(Paths.get(workbookPath).toFile());
    }

    @Override
    public String getWorkbookPath() {
        return workbookPath;
    }

    @Override
    public void setWorkbookPath(String workbookPath) {
        this.workbookPath = workbookPath;
    }
}
