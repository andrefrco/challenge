package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipPersistenceDTO;
import com.contaazul.bankslips.exception.InvalidBankslipProvidedException;
import com.contaazul.bankslips.repository.BankslipRepository;
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

public class PersistBankslipTest {

    @InjectMocks
    @Resource
    private PersistBankslip persistBankslip;

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
    public void persistTestSucess() throws ParseException {
        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .customer( "Trillian Company" )
                .dueDate( formatterDate( "2018-10-10" ) )
                .priceInCents( new BigDecimal(99000 ) )
                .build();

        persistBankslip.persist( bankslipPersistenceDTO );
    }

    @Test(expected = InvalidBankslipProvidedException.class)
    public void persistTestWithCustomerEmpty() throws ParseException {
        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .dueDate( formatterDate( "2018-10-10" ) )
                .priceInCents( new BigDecimal( 99000 ) )
                .build();

        persistBankslip.persist( bankslipPersistenceDTO );
    }

    @Test(expected = InvalidBankslipProvidedException.class)
    public void persistTestWithDueDateEmpty() throws ParseException {
        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .build();

        persistBankslip.persist( bankslipPersistenceDTO );
    }

    @Test(expected = InvalidBankslipProvidedException.class)
    public void persistTestWithPriceInCentsEmpty() throws ParseException {
        BankslipPersistenceDTO bankslipPersistenceDTO = BankslipPersistenceDTO.builder()
                .customer( "Trillian Company" )
                .dueDate( formatterDate( "2018-10-10" ) )
                .build();

        persistBankslip.persist( bankslipPersistenceDTO );
    }


}
