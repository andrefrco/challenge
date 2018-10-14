package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private FindBankslip findBankslip;

    public BankslipDTO findDetail(String id) throws NotFoundException {
        Bankslip bankslip = findBankslip.findById( id );
        if (bankslip.getStatus().equals( BankslipStatus.PAID )) {
            BigDecimal fine = calculateFine( bankslip.getDueDate(), bankslip.getPaymentDate(), bankslip.getPriceInCents() );
            return new BankslipDTO( bankslip, fine );
        }
        return new BankslipDTO( bankslip );
    }

    private BigDecimal calculateFine(Date dueDate, Date nowDate, BigDecimal price) {
        int intervalDays = getIntervalDays( dueDate, nowDate );
        BigDecimal taxFine = getTaxFine( intervalDays );
        return price.multiply( taxFine.multiply( BigDecimal.valueOf( intervalDays ) ) ).setScale( 0, RoundingMode.HALF_EVEN );
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
