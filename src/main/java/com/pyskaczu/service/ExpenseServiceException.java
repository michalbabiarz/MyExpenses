package com.pyskaczu.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExpenseServiceException extends Exception {

    public ExpenseServiceException(String message) {
        super(message);
    }

    public ExpenseServiceException(Throwable throwable) {
        super(throwable);
    }
}
