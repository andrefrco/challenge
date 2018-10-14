package com.contaazul.bankslips.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidBankslipProvidedException extends RuntimeException{

    public InvalidBankslipProvidedException(String message) {
        super(message);
    }
}
