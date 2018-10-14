package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.dto.PaymentBankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest")
public class BankslipController {

    @Autowired
    private PersistBankslip persistBankslip;

    @Autowired
    private FindBankslip findBankslip;

    @Autowired
    private FineCalculate fineCalculate;

    @Autowired
    private PayBankslip payBankslip;

    @Autowired
    private CancelBankslip cancelBankslip;

    @PostMapping(value = "/bankslips")
    public ResponseEntity<BankslipDTO> persist(@RequestBody BankslipPersistenceDTO bankslipPersistenceDTO) {
        Bankslip bankslipSaved = persistBankslip.persist( bankslipPersistenceDTO );
        return new ResponseEntity<>( new BankslipDTO( bankslipSaved ), HttpStatus.CREATED );
    }

    @GetMapping(value = "/bankslips")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>( findBankslip.findAll(), HttpStatus.OK );
    }

    @GetMapping(value = "/bankslips/{id}")
    public ResponseEntity<BankslipDTO> findById(@PathVariable("id") String id) throws NotFoundException {
        return new ResponseEntity<>( new BankslipDTO( fineCalculate.persistFine( id ) ), HttpStatus.OK );
    }

    @PostMapping(value = "/bankslips/{id}/payments")
    public HttpStatus pay(@RequestBody PaymentBankslipDTO paymentDTO, @PathVariable("id") String id) throws NotFoundException {
        payBankslip.pay( id, paymentDTO );
        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping(value = "/bankslips/{id}")
    public HttpStatus cancel(@PathVariable("id") String id ) throws NotFoundException {
        cancelBankslip.cancel( id );
        return HttpStatus.NO_CONTENT;
    }
}
