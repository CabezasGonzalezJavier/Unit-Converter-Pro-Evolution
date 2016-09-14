package com.thedeveloperworldisyours.unitconverterpro.currency;

import com.thedeveloperworldisyours.unitconverterpro.client.ClientAsyncTask;
import com.thedeveloperworldisyours.unitconverterpro.model.Rates;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by javierg on 22/08/16.
 */
public class CurrencyInterceptorTest {

    private ClientAsyncTask mClientAsyncTask;
    private String mResponseString;
    private CurrencyInterceptorImpl mCurrencyInterceptor;
    private Rates mRates;

    @Before
    public void setUp(){

        mClientAsyncTask = mock(ClientAsyncTask.class);

        mRates = new Rates();

        mRates.setAUD(1.2);
        mRates.setBGN(3.1);
        mRates.setBRL(5.1);

        mRates.setCAD(4.3);
        mRates.setCHF(40.2);
        mRates.setCNY(0.336);

        mRates.setCZK(6.3);
        mRates.setGBP(2.2);
        mRates.setHKD(1.0);

        mRates.setHRK(8.3);
        mRates.setIDR(5.3);
        mRates.setRUB(1.3);

        mRates.setSEK(32.3);
        mRates.setSGD(50.2);
        mRates.setTHB(0.01);

        mRates.setTRY(36.3);
        mRates.setUSD(0.23);
        mRates.setZAR(3.3);

        mResponseString = "Something";
        mCurrencyInterceptor = mock(CurrencyInterceptorImpl.class);
    }

    @Test
    public void testCurrency(){

        assertEquals(mRates.getAUD(), 1.2);
        assertEquals(mRates.getBGN(), 3.1);
        assertEquals(mRates.getBRL(), 5.1);
    }
}
