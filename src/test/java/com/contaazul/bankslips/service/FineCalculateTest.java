package com.contaazul.bankslips.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.util.Date;
import java.util.GregorianCalendar;


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


}
