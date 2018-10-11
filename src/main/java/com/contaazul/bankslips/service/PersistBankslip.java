package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersistBankslip {

    @Autowired
    BankslipRepository bankslipRepository;

    @Autowired
    ModelMapper modelMapper;

    public Bankslip persist(BankslipPersistenceDTO bankslipPersistenceDTO) {
        return bankslipRepository.save( convertToEntity( bankslipPersistenceDTO ) );
    }

    public Bankslip convertToEntity(BankslipPersistenceDTO bankslipPersistenceDTO) {
        Bankslip bankslip = modelMapper.map( bankslipPersistenceDTO, Bankslip.class );
        bankslip.setId( UUID.randomUUID().toString() );
        bankslip.setStatus( BankslipStatus.PENDING );
        return bankslip;
    }
}
