package com.thedeveloperworldisyours.unitconverterpro;

import android.support.test.InstrumentationRegistry;

import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.Rate;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by javierg on 25/08/16.
 */
public class SQLiteTest {

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

    @Test
    public void testPreConditions() {
        assertNotNull(mRateDataSource);
    }


    @Test
    public void shouldAddExpenseType() throws Exception {
        mRateDataSource.createRate("AUD", 1.2);

        List<Rate> rate = mRateDataSource.getAllRates();
        assertThat(rate.size(), is(1));
        assertTrue(rate.get(0).toString().equals("AUD"));
    }

}
