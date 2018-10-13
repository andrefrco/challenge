package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.dto.PaymentDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.service.FindBankslip;
import com.contaazul.bankslips.service.FineCalculate;
import com.contaazul.bankslips.service.PayBankslip;
import com.contaazul.bankslips.service.PersistBankslip;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest")
public class BankslipController {

    @Autowired
    PersistBankslip persistBankslip;

    @Autowired
    FindBankslip findBankslip;

    @Autowired
    FineCalculate fineCalculate;

    @Autowired
    PayBankslip payBankslip;

    @PostMapping(value = "/bankslips")
    public ResponseEntity<BankslipDTO> persist(@RequestBody BankslipPersistenceDTO bankslipPersistenceDTO) {
        Bankslip bankslipSaved = persistBankslip.persist( bankslipPersistenceDTO );
        return new ResponseEntity( new BankslipDTO( bankslipSaved ), HttpStatus.CREATED );
    }

    @GetMapping(value = "/bankslips")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity( findBankslip.findAll(), HttpStatus.OK );
    }

    @GetMapping(value = "/bankslips/{id}")
    public ResponseEntity<BankslipDTO> findById(@PathVariable("id") String id) throws NotFoundException {
        return new ResponseEntity( new BankslipDTO( fineCalculate.persistFine( id ) ), HttpStatus.OK );
    }

    @PostMapping(value = "/bankslips/{id}/payments")
    public ResponseEntity<BankslipDTO> pay(@RequestBody PaymentDTO paymentDTO, @PathVariable("id") String id) throws NotFoundException {
        payBankslip.pay( id, paymentDTO );
        return new ResponseEntity( HttpStatus.NO_CONTENT );
    }
}
