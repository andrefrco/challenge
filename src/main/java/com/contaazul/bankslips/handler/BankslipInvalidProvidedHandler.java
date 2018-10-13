package com.contaazul.bankslips.handler;

import com.contaazul.bankslips.exception.BankslipInvalidProvidedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankslipInvalidProvidedHandler {

    @ExceptionHandler(BankslipInvalidProvidedException.class)
    public ResponseEntity<?> bankslipInvalidProvidedHandler(Exception e) {
        return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY ).body( e.getMessage() );
    }

}
