package com.thedeveloperworldisyours.unitconverterpro.currency;

import android.support.annotation.NonNull;
import android.util.Log;

import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;
import com.thedeveloperworldisyours.unitconverterpro.webservice.CurrencyResponseHandler;

/**
 * Created by javierg on 17/08/16.
 */
public class CurrencyPresenterImpl implements CurrencyPresenter, CurrencyResponseHandler {

    private static final String TAG = "CurrencyPresenterImpl";
    @NonNull
    private CurrencyInterceptor mCurrencyInterceptor;
    @NonNull
    private CurrencyView mCurrencyView;
    @NonNull
    private RateDataSource mRateDataSource;


    public CurrencyPresenterImpl(@NonNull CurrencyView currencyView,
                                 @NonNull RateDataSource rateDataSource) {
        this.mCurrencyView = currencyView;
        this.mRateDataSource = rateDataSource;
    }

    @Override
    public void callService() {
        mCurrencyView.showProgressBar();
        mCurrencyInterceptor = new CurrencyInterceptorImpl();
        mCurrencyInterceptor.getCurrency(this);

    }

    @Override
    public void successful(Currency currency) {

        insertRateDataBase(currency);
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

    public void insertRateDataBase(Currency currency) {

        mRateDataSource.deleteAll();

        mRateDataSource.createRate("AUD", currency.getRates().getAUD());//0
        mRateDataSource.createRate("BGN", currency.getRates().getBGN());//1
        mRateDataSource.createRate("BRL", currency.getRates().getBRL());//2

        mRateDataSource.createRate("CAD", currency.getRates().getCAD());//3
        mRateDataSource.createRate("CHF", currency.getRates().getCHF());//4
        mRateDataSource.createRate("CNY", currency.getRates().getCNY());//5

        mRateDataSource.createRate("CZK", currency.getRates().getCZK());//6
        mRateDataSource.createRate("DKK", currency.getRates().getDKK());//7
        mRateDataSource.createRate("GBP", currency.getRates().getGBP());//8

        mRateDataSource.createRate("HKD", currency.getRates().getHKD());//9
        mRateDataSource.createRate("HRK", currency.getRates().getHRK());//10
        mRateDataSource.createRate("HUF", currency.getRates().getHUF());//11

        mRateDataSource.createRate("IDR", currency.getRates().getIDR());//12
        mRateDataSource.createRate("ILS", currency.getRates().getILS());//13
        mRateDataSource.createRate("INR", currency.getRates().getINR());//14

        mRateDataSource.createRate("JPY", currency.getRates().getJPY());//15
        mRateDataSource.createRate("KRW", currency.getRates().getKRW());//16
        mRateDataSource.createRate("MXN", currency.getRates().getMXN());//17

        mRateDataSource.createRate("MYR", currency.getRates().getMYR());//18
        mRateDataSource.createRate("NOK", currency.getRates().getNOK());//19
        mRateDataSource.createRate("NZD", currency.getRates().getNZD());//20

        mRateDataSource.createRate("PHP", currency.getRates().getPHP());//21
        mRateDataSource.createRate("PLN", currency.getRates().getPLN());//22
        mRateDataSource.createRate("RON", currency.getRates().getRON());//23

        mRateDataSource.createRate("RUB", currency.getRates().getRUB());//24
        mRateDataSource.createRate("SEK", currency.getRates().getSEK());//25
        mRateDataSource.createRate("SGD", currency.getRates().getSGD());//26

        mRateDataSource.createRate("THB", currency.getRates().getTHB());//27
        mRateDataSource.createRate("TRY", currency.getRates().getTRY());//28
        mRateDataSource.createRate("USD", currency.getRates().getUSD());//29

        mRateDataSource.createRate("ZAR", currency.getRates().getZAR());//30
        Log.d(TAG, "INSERTED IN DATABASE");
        mCurrencyView.hideProgressBar();
        mCurrencyView.successful(currency);
    }
}
