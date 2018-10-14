package com.contaazul.bankslips.service;

import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FindBankslipTest {

    @InjectMocks
    @Resource
    private FindBankslip findBankslip;

    @Mock
    private BankslipRepository bankslipRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Date formatterDate(String dueDate) throws ParseException {
        return formatter.parse(dueDate);
    }

    @Test
    public void findByIdReturnOne() throws ParseException, NotFoundException {
        String uuid = UUID.randomUUID().toString();
        Bankslip bankslip = Bankslip.builder()
                .id( uuid )
                .dueDate( formatterDate( "2018-10-10" ) )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PENDING )
                .build();
        bankslipRepository.save( bankslip );
        findBankslip.findById( uuid );
    }

}
