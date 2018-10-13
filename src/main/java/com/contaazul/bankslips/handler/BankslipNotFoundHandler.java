package com.contaazul.bankslips.handler;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankslipNotFoundHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> bankslipNotProvidedHandler(Exception e) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
    }

}
