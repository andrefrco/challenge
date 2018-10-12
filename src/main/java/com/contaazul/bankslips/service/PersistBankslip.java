package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import java.util.UUID;

@Service
public class PersistBankslip {

    @Autowired
    BankslipRepository bankslipRepository;

    public Bankslip persist(BankslipPersistenceDTO bankslipPersistenceDTO) {
        validateBankslipPersistenceDTOFields(bankslipPersistenceDTO);
        return bankslipRepository.save( convertToEntity( bankslipPersistenceDTO ) );
    }

    public Bankslip convertToEntity(BankslipPersistenceDTO bankslipPersistenceDTO) {
        return bankslipPersistenceDTO.toBankSlip( UUID.randomUUID().toString(), BankslipStatus.PENDING );
    }

    public void validateBankslipPersistenceDTOFields(BankslipPersistenceDTO bankslipPersistenceDTO) {
        Validation.buildDefaultValidatorFactory()
                .getValidator().validate(bankslipPersistenceDTO).toArray();
    }
}
