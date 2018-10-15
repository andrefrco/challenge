package com.contaazul.bankslips.service;

import com.contaazul.bankslips.dto.BankslipDTO;
import com.contaazul.bankslips.entity.Bankslip;
import com.contaazul.bankslips.entity.BankslipStatus;
import com.contaazul.bankslips.repository.BankslipRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
        Bankslip bankslip = buildBankslip( uuid );
        FindBankslip findBankslip = Mockito.mock( FindBankslip.class );
        when( findBankslip.findById( uuid ) ).thenReturn( bankslip );
        assertTrue( bankslip.equals( findBankslip.findById( uuid ) ) );
    }

    @Test( expected = NotFoundException.class)
    public void findByIdReturnException() throws ParseException, NotFoundException {
        UUID.randomUUID().toString();
        Bankslip bankslip = buildBankslip( UUID.randomUUID().toString() );
        bankslipRepository.save( bankslip );
        findBankslip.findById( UUID.randomUUID().toString() );
    }

    @Test
    public void findAll() throws ParseException {
        BankslipDTO bankslipDTO = new BankslipDTO( buildBankslip( UUID.randomUUID().toString() ) );
        List<BankslipDTO> mocks = new ArrayList<>();
        mocks.add( bankslipDTO );
        FindBankslip findBankslip = Mockito.mock( FindBankslip.class );
        when( findBankslip.findAll() ).thenReturn( mocks );
        assertTrue( mocks.equals( findBankslip.findAll() ) );
    }

    private Bankslip buildBankslip(String uuid) throws ParseException {
        return Bankslip.builder()
                .id( uuid )
                .dueDate( formatterDate( "2018-10-10" ) )
                .customer( "Trillian Company" )
                .priceInCents( new BigDecimal( 99000 ) )
                .status( BankslipStatus.PENDING )
                .build();
    }

}
