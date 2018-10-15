package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FineCalculateTest {

    @InjectMocks
    @Resource
    private FineCalculate fineCalculate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private static Date PAYMENT_DATE = new GregorianCalendar(2018, 10, 14).getTime();

    private static Date DUE_DATE = new GregorianCalendar(2018, 10, 11).getTime();

    private static Date BEFORE_DUE_DATE = new GregorianCalendar(2018, 10, 2).getTime();

    @Test
    public void fineCalculateWhenLowerTax() throws ParseException {
        Bankslip bankslip = Bankslip.builder()
                .id( UUID.randomUUID().toString() )
                .dueDate( DUE_DATE )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PAID )
                .paymentDate( PAYMENT_DATE )
                .build();

        BankslipDTO bankslipDTO = fineCalculate.findDetail(bankslip);
        assertEquals( new BigDecimal( 1485 ), bankslipDTO.getFine() );
    }

    @Test
    public void fineCalculateWhenHigherTax() {
        Bankslip bankslip = Bankslip.builder()
                .id( UUID.randomUUID().toString() )
                .dueDate( BEFORE_DUE_DATE )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PAID )
                .paymentDate( PAYMENT_DATE )
                .build();

        BankslipDTO bankslipDTO = fineCalculate.findDetail(bankslip);
        assertEquals( new BigDecimal( 11880 ), bankslipDTO.getFine() );
    }

    @Test
    public void fineCalculateWhenWhenNotExistsDiffDay() {
        Bankslip bankslip = Bankslip.builder()
                .id( UUID.randomUUID().toString() )
                .dueDate( DUE_DATE )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PAID )
                .paymentDate( DUE_DATE )
                .build();

        BankslipDTO bankslipDTO = fineCalculate.findDetail(bankslip);
        assertEquals( new BigDecimal( 0 ), bankslipDTO.getFine() );
    }

    @Test
    public void fineCalculateWhenNotPaid() throws ParseException {
        Bankslip bankslip = Bankslip.builder()
                .id( UUID.randomUUID().toString() )
                .dueDate( DUE_DATE )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PENDING )
                .build();

        BankslipDTO bankslipDTO = fineCalculate.findDetail(bankslip);
        assertEquals( null, bankslipDTO.getFine() );
    }


}
