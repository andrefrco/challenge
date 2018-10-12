package com.contaazul.bankslips.service;

import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.repository.BankslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindBankslip {

    @Autowired
    BankslipRepository bankslipRepository;

    public List<Bankslip> findAll() {
        return bankslipRepository.findAll();
    }
}
