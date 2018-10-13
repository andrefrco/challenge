package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.exception.BankslipInvalidProvidedException;
import com.contaazul.bankslips.repository.BankslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;
import java.util.UUID;

@Service
public class PersistBankslip {

    private static final String MESSAGE_INVALID_BANKSLIP = "Invalid bankslip provided. The possible reasons are: ";

    @Autowired
    BankslipRepository bankslipRepository;

    public Bankslip persist(BankslipPersistenceDTO bankslipPersistenceDTO) {
        validateBankslipPersistenceDTOFields(bankslipPersistenceDTO);
        return bankslipRepository.save( convertToEntity( bankslipPersistenceDTO ) );
    }

    private Bankslip convertToEntity(BankslipPersistenceDTO bankslipPersistenceDTO) {
        return bankslipPersistenceDTO.toBankSlip( UUID.randomUUID().toString(), BankslipStatus.PENDING );
    }

    private void validateBankslipPersistenceDTOFields(BankslipPersistenceDTO bankslipPersistenceDTO) {
        Set<ConstraintViolation<BankslipPersistenceDTO>> violations = Validation.buildDefaultValidatorFactory()
                .getValidator().validate(bankslipPersistenceDTO);
        if (violations.size() > 0) {
            throw new BankslipInvalidProvidedException( buildErrorReturn( violations ) );
        }
    }

    private String buildErrorReturn(Set<ConstraintViolation<BankslipPersistenceDTO>> violations) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( MESSAGE_INVALID_BANKSLIP );
        violations.stream().forEach(v -> {
            stringBuilder.append( v.getPropertyPath() ).append(" ").append( v.getMessage() );
        });
        return stringBuilder.toString();
    }
}
