package com.contaazul.bankslips.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BankslipNotProvidedException extends RuntimeException{

    private static final String MESSAGE = "Bankslip not provided in the request body";

    public BankslipNotProvidedException() {
        super(MESSAGE);
    }
}
