package com.thedeveloperworldisyours.unitconverterpro;

import android.support.test.rule.ActivityTestRule;

import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.Rate;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by javierg on 26/08/16.
 */
public class CurrencyFragmentTest {

    private RateDataSource mRateDataSource;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

        mRateDataSource = mock(RateDataSource.class);
        mRateDataSource.open();
    }

    @After
    public void tearDown() throws Exception {
        mRateDataSource.close();
    }

    @Test
    public void shouldAddExpenseType() throws Exception {
        mRateDataSource.createRate("AUD", 1.2);

        List<Rate> rate = mRateDataSource.getAllRates();
        assertThat(rate.size(), is(1));
        assertTrue(rate.get(0).toString().equals("AD"));
    }
}
