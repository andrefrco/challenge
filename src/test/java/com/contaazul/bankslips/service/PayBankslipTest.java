package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.PaymentBankslipDTO;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.GregorianCalendar;
import java.util.UUID;

public class PayBankslipTest {

    @Mock
    private PayBankslip payBankslip;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void payBankslipTest() throws NotFoundException {
        String uuid = UUID.randomUUID().toString();
        PaymentBankslipDTO paymentBankslipDTO = PaymentBankslipDTO
                .builder()
                .paymentDate( new GregorianCalendar(2018, 10, 14).getTime() )
                .build();

        payBankslip.pay( uuid, paymentBankslipDTO );
    }

}
