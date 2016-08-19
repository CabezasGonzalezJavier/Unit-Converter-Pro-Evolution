package com.thedeveloperworldisyours.unitconverterpro.interceptor;

import com.thedeveloperworldisyours.unitconverterpro.webservice.CurrencyResponseHandler;

/**
 * Created by javierg on 17/08/16.
 */
public interface CurrencyInterceptor {
    public void     getCurrency(CurrencyResponseHandler mCurrencyResponseHandler);
}
