package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindBankslip {

    @Autowired
    private BankslipRepository bankslipRepository;

    public List<BankslipDTO> findAll() {
        return bankslipRepository.findAll().stream().map( BankslipDTO::new ).collect( Collectors.toList() );
    }

    public Bankslip findById(String id) throws NotFoundException {
        return bankslipRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( "Bankslip not found with the specified id: " + id ));
    }
}
