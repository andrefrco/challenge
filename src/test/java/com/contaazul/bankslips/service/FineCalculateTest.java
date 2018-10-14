package com.contaazul.bankslips.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class FineCalculateTest {

    @InjectMocks
    @Resource
    private FineCalculate fineCalculate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    private static Date NOW_DATE = new GregorianCalendar(2018, 10, 14).getTime();

    private static Date DUE_DATE = new GregorianCalendar(2018, 10, 11).getTime();

    private static Date BEFORE_DUE_DATE = new GregorianCalendar(2018, 10, 2).getTime();

    @Test
    public void getIntervalDaysTestWhenExistsDiff() {
        int interval = fineCalculate.getIntervalDays( DUE_DATE, NOW_DATE );
        assertEquals( 3, interval );
    }

    @Test
    public void getIntervalDaysTestWhenNotExistsDiff() {
        int interval = fineCalculate.getIntervalDays( DUE_DATE, DUE_DATE );
        assertEquals( 0, interval );
    }

    @Test
    public void getIntervalDaysTestWhenInvertDates() {
        int interval = fineCalculate.getIntervalDays( NOW_DATE, DUE_DATE );
        assertEquals( 0, interval );
    }

    @Test
    public void getTaxFineLower() {
        BigDecimal tax = fineCalculate.getTaxFine(9);
        assertEquals( new BigDecimal( "0.005" ), tax );
    }

    @Test
    public void getTaxFineHigher() {
        BigDecimal tax = fineCalculate.getTaxFine(11);
        assertEquals( new BigDecimal( "0.01" ), tax );
    }

    @Test
    public void calculateFineSuccessTest() {
        BigDecimal fine = fineCalculate.calculateFine( DUE_DATE, NOW_DATE, new BigDecimal(99000 ) );
        assertEquals( new BigDecimal( 1485 ), fine );
    }

    @Test
    public void calculateFineSameDateTest() {
        BigDecimal fine = fineCalculate.calculateFine( DUE_DATE, DUE_DATE, new BigDecimal(99000 ) );
        assertEquals( new BigDecimal( 0 ), fine );
    }

    @Test
    public void calculateFineWithDiffValueTest() {
        BigDecimal fine = fineCalculate.calculateFine( DUE_DATE, NOW_DATE, new BigDecimal(1000 ) );
        assertEquals( new BigDecimal( 15 ), fine );
    }

    @Test
    public void calculateFineSuccesWithDiffPriceTest() {
        BigDecimal fine = fineCalculate.calculateFine( BEFORE_DUE_DATE, NOW_DATE, new BigDecimal(99000 ) );
        assertEquals( new BigDecimal( 11880 ), fine );
    }


}
