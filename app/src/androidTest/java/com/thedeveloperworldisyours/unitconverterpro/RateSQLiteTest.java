package com.thedeveloperworldisyours.unitconverterpro;

import android.support.test.InstrumentationRegistry;

import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.Rate;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by javierg on 25/08/16.
 */
public class RateSQLiteTest {

    private RateDataSource mRateDataSource;

    @Before
    public void setUp() {

        mRateDataSource = new RateDataSource(InstrumentationRegistry.getTargetContext());
        mRateDataSource.open();
    }

    @After
    public void cleanUp() {
        mRateDataSource.deleteAll();
        mRateDataSource.close();
    }

    @After
    public void finish() {
        mRateDataSource.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mRateDataSource);
    }

    @Test
    public void testShouldAddExpenseType() throws Exception {
        mRateDataSource.createRate("AUD", 1.2);
        List<Rate> rate = mRateDataSource.getAllRates();

        assertThat(rate.size(), is(1));
        assertTrue(rate.get(0).toString().equals("AUD"));
        assertTrue(rate.get(0).getValue().equals(1.2));
    }

    @Test
    public void testDeleteAll() {
        mRateDataSource.deleteAll();
        List<Rate> rate = mRateDataSource.getAllRates();

        assertThat(rate.size(), is(0));
    }

    @Test
    public void testDeleteOnlyOne() {
        mRateDataSource.createRate("AUD", 1.2);
        List<Rate> rate = mRateDataSource.getAllRates();

        assertThat(rate.size(), is(1));

        mRateDataSource.deleteRate(rate.get(0));
        rate = mRateDataSource.getAllRates();

        assertThat(rate.size(), is(0));
    }

}
