package com.contaazul.bankslips.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankslipNotProvidedHandler {

    private static final String MESSAGE = "Bankslip not provided in the request body";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> bankslipNotProvidedHandler() {
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( MESSAGE );
    }
}
