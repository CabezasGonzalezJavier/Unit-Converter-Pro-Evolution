package com.thedeveloperworldisyours.unitconverterpro.presenter;

import com.thedeveloperworldisyours.unitconverterpro.interceptor.CurrencyInterceptor;
import com.thedeveloperworldisyours.unitconverterpro.interceptor.CurrencyInterceptorImpl;
import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.view.CurrencyView;
import com.thedeveloperworldisyours.unitconverterpro.webservice.CurrencyResponseHandler;

/**
 * Created by javierg on 17/08/16.
 */
public class CurrencyPresenterImpl implements CurrencyPresenter, CurrencyResponseHandler {

    private CurrencyInterceptor mCurrencyInterceptor;
    private CurrencyView mCurrencyView;

    public CurrencyPresenterImpl(CurrencyView mCurrencyView) {
        this.mCurrencyView = mCurrencyView;
    }

    @Override
    public void callService() {
        mCurrencyView.showProgressBar();
        mCurrencyInterceptor = new CurrencyInterceptorImpl();
        mCurrencyInterceptor.getCurrency(this);
    }

    @Override
    public void successful(Currency currency) {
        mCurrencyView.hideProgressBar();
        mCurrencyView.successful(currency);
    }

    @Override
    public void error(String error) {
        mCurrencyView.hideProgressBar();
        mCurrencyView.error();
    }

    @Override
    public void generalError() {
        mCurrencyView.hideProgressBar();
        mCurrencyView.generalError();
    }
}
