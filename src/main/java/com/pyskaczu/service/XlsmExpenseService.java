package com.pyskaczu.service;

import com.google.inject.Inject;
import com.pyskaczu.model.Configuration;
import com.pyskaczu.model.Expense;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class XlsmExpenseService implements IExpenseService {
    private static final int FIRST_EXPENSE_ROW = 7;
    private static final int FIRST_DATA_COLUMN = 1;

    private static final boolean WRITE_FILE = true;
    private static final boolean NO_WRITE_FILE = false;

    @Inject
    private Configuration configuration;

    @Override
    public List<Expense> findAllExpenses() throws ExpenseServiceException {
        Function<Sheet, List<Expense>> findAllExpensesFunction = (sheet -> {
            List<Expense> expenses = new ArrayList<>();

            for(int i = FIRST_EXPENSE_ROW; !sheet.getRow(i).getCell(FIRST_DATA_COLUMN).toString().isEmpty(); i++) {
                Row row = sheet.getRow(i);
                System.out.print("Line " + (i - FIRST_EXPENSE_ROW) + ": ");
                row.cellIterator().forEachRemaining(cell -> System.out.print(cell.toString()+","));
                System.out.println();
            }

            return expenses;
        });
        return performSheetOperation(findAllExpensesFunction, NO_WRITE_FILE);
    }

    @Override
    public Expense findLastExpense() throws ExpenseServiceException {
        List<Expense> expenses = findAllExpenses();
        return expenses.get(expenses.size());
    }

    @Override
    public void saveExpense(Expense expense) throws ExpenseServiceException {
        if(null == expense) throw new IllegalArgumentException("Expense was null");
        Function<Sheet, List<Expense>> saveExpensesFunction = (sheet -> {
            int i = 0;
            for(i = FIRST_EXPENSE_ROW; !sheet.getRow(i).getCell(FIRST_DATA_COLUMN).toString().isEmpty(); i++);
            sheet.getRow(i).getCell(1).setCellValue(expense.getDate());
            sheet.getRow(i).getCell(2).setCellValue(expense.getAmmount());
            sheet.getRow(i).getCell(3).setCellValue(expense.getCategory());
            sheet.getRow(i).getCell(4).setCellValue(expense.getSubcategory());
            sheet.getRow(i).getCell(5).setCellValue(expense.getType());
            sheet.getRow(i).getCell(6).setCellValue(expense.getComment());

            return null;
        });

        performSheetOperation(saveExpensesFunction, WRITE_FILE);
    }

    private List<Expense> performSheetOperation(Function<Sheet, List<Expense>> function, boolean writeFile) throws ExpenseServiceException {

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(configuration.workbookFileLocation));
            Workbook wb = new XSSFWorkbook(in);
            String currentMonthSheetName = com.pyskaczu.enums.Sheet.CURRENT_MONTH.getName();
            Sheet sheet = wb.getSheet(currentMonthSheetName);
            if(null == sheet.getSheetName()) {
                throw new ExpenseServiceException("\"" + currentMonthSheetName+"\" sheet NOT found. Please add it in the desktop app");
            }

            List<Expense> expenses = function.apply(sheet);
            in.close();

            if(writeFile) {
                try (FileOutputStream out = new FileOutputStream(configuration.workbookFileLocation)) {
                    wb.write(out);
                }
            }

            return expenses;

        } catch (IOException e) {
            throw new ExpenseServiceException(e);
        }
    }
}
