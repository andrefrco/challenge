package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.service.FindBankslip;
import com.contaazul.bankslips.service.FineCalculate;
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
        Bankslip bankslipCalculatedFine = fineCalculate.persistFine( findBankslip.findById( id ) );
        return new ResponseEntity( new BankslipDTO( bankslipCalculatedFine ), HttpStatus.OK );
    }
}
