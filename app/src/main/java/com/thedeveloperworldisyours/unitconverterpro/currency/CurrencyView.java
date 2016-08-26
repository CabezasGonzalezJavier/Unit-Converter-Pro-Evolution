package com.thedeveloperworldisyours.unitconverterpro.currency;

import com.thedeveloperworldisyours.unitconverterpro.model.Currency;

/**
 * Created by javierg on 17/08/16.
 */
public interface CurrencyView {
    public void successful(Currency currency);
    public void error();
    public void generalError();
    public void showProgressBar();
    public void hideProgressBar();
}
