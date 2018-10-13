package com.contaazul.bankslips.service;

import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class FineCalculate {

    private static int CUT_OF_DATE =  10;

    private static BigDecimal LOWER_TAX = new BigDecimal("0.005");

    private static BigDecimal HIGHER_TAX = new BigDecimal("0.01");

    @Autowired
    BankslipRepository bankslipRepository;

    @Autowired
    FindBankslip findBankslip;

    public Bankslip persistFine(String id) throws NotFoundException {
        Bankslip bankslip = findBankslip.findById( id );
        Date now = new Date();
        bankslip.setFine( calculateFine( bankslip.getDueDate(), now, bankslip.getPriceInCents() ) );
        bankslip.setStatus( BankslipStatus.PAID );
        bankslip.setPaymentDate( now );
        bankslipRepository.save( bankslip );
        return bankslip;
    }

    private BigDecimal calculateFine(Date dueDate, Date nowDate, BigDecimal price) {
        int intervalDays = getIntervalDays( dueDate, nowDate );
        BigDecimal taxFine = getTaxFine( intervalDays );
        return price.multiply( taxFine.multiply( BigDecimal.valueOf( intervalDays ) ) );
    }

    private int getIntervalDays(Date dueDate, Date nowDate) {
        int intervalInDays = Period.between( convertToLocalDate( dueDate ), convertToLocalDate( nowDate ) ).getDays();
        if (intervalInDays > 0) {
            return intervalInDays;
        }
        return 0;
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
    }

    private BigDecimal getTaxFine(int intervalInDays) {
        if (intervalInDays < CUT_OF_DATE) {
            return LOWER_TAX;
        }
        return HIGHER_TAX;
    }

}
