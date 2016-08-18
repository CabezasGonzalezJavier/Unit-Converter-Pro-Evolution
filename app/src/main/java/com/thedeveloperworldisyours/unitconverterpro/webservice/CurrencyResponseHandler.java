package com.thedeveloperworldisyours.unitconverterpro.webservice;

import com.thedeveloperworldisyours.unitconverterpro.model.Currency;

/**
 * Created by javierg on 18/08/16.
 */
public interface CurrencyResponseHandler {
    public void successful(Currency currency );
    public void error(String error);
    public void generalError();
}
