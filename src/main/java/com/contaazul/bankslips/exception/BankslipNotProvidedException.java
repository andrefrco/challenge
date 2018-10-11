package com.contaazul.bankslips.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BankslipNotProvidedException extends RuntimeException{

    public BankslipNotProvidedException() {
        super();
    }

    public BankslipNotProvidedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankslipNotProvidedException(String message) {
        super(message);
    }

    public BankslipNotProvidedException(Throwable cause) {
        super(cause);
    }
}
