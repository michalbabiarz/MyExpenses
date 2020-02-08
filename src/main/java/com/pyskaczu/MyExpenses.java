package com.pyskaczu;

import com.pyskaczu.enums.Sheet;
import com.pyskaczu.model.Expense;
import com.pyskaczu.service.ExpenseServiceException;
import com.pyskaczu.service.IExpenseService;
import com.pyskaczu.service.XlsmExpenseService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

public class MyExpenses {
    private static final Path workFilePath = Paths.get("Budget.xlsm");

    public static void main(String[] args) throws ExpenseServiceException {
        System.out.println("Hello World!");
        System.out.println(Files.exists(workFilePath));

        XlsmExpenseService service = new XlsmExpenseService();
        service.findAllExpenses();
        service.saveExpense(new Expense(LocalDate.now(), 100, "Dupa", "Dom", "type", "kom"));
        service.findAllExpenses();

//        String workbookWebLocation = "https://1drv.ms/x/s!ApSdPXaH5nAqiB9aLibOkWvb68aM";
    }
}
