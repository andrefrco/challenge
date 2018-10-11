package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.service.PersistBankslip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class BankslipController {

    @Autowired
    PersistBankslip persistBankslip;

    @PostMapping(value = "/bankslips")
    public ResponseEntity<BankslipDTO> persist(@RequestBody BankslipPersistenceDTO bankslipPersistenceDTO) {
        Bankslip bankslipSaved = persistBankslip.persist( bankslipPersistenceDTO );
        return new ResponseEntity( new BankslipDTO(bankslipSaved), HttpStatus.CREATED );
    }
}
