package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.service.FindBankslip;
import com.contaazul.bankslips.service.PersistBankslip;
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

    @PostMapping(value = "/bankslips")
    public ResponseEntity<BankslipDTO> persist(@RequestBody BankslipPersistenceDTO bankslipPersistenceDTO) {
        Bankslip bankslipSaved = persistBankslip.persist( bankslipPersistenceDTO );
        return new ResponseEntity( new BankslipDTO(bankslipSaved), HttpStatus.CREATED );
    }

    @GetMapping(value = "/bankslips")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status( HttpStatus.OK ).body( findBankslip.findAll() );
    }
}
