package com.contaazul.bankslips.service;

import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelBankslip {

    @Autowired
    FindBankslip findBankslip;

    @Autowired
    BankslipRepository bankslipRepository;

    public void cancel(String id) throws NotFoundException {
        Bankslip bankslip = findBankslip.findById( id );
        bankslip.setStatus(BankslipStatus.CANCELED );
        bankslipRepository.save(bankslip);
    }
}
