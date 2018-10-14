package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.PaymentBankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayBankslip {

    @Autowired
    private FindBankslip findBankslip;

    @Autowired
    private BankslipRepository bankslipRepository;

    public void pay(String id, PaymentBankslipDTO paymentDTO) throws NotFoundException {
        Bankslip bankslip = findBankslip.findById( id );
        bankslip.setPaymentDate( paymentDTO.getPaymentDate() );
        bankslipRepository.save(bankslip);
    }

}
