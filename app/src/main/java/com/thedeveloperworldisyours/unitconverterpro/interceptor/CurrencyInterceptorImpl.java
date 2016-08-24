package com.thedeveloperworldisyours.unitconverterpro.interceptor;

import android.util.Log;

import com.google.gson.Gson;
import com.thedeveloperworldisyours.unitconverterpro.common.utils.Constant;
import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.webservice.CommandFactory;
import com.thedeveloperworldisyours.unitconverterpro.webservice.CurrencyResponseHandler;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseHandler;

/**
 * Created by javierg on 17/08/16.
 */
    public class CurrencyInterceptorImpl implements CurrencyInterceptor {

    private static final String TAG = "CurrencyInterceptorImpl";
    private CommandFactory mCommandFactory = new CommandFactory();

    @Override
    public void getCurrency(final CurrencyResponseHandler mCurrencyResponseHandler) {
            ResponseHandler responseHandler = new ResponseHandler() {
            @Override
            public void sendResponseSuccessful(String response) {

                mCurrencyResponseHandler.successful(changesResponseToCurrency(response));

            }

            @Override
            public void sendResponseFail(String error) {
                mCurrencyResponseHandler.error(error);
            }

            @Override
            public void sendResponseGeneralError() {
                mCurrencyResponseHandler.generalError();
            }
        };
        mCommandFactory.createGetCommand(Constant.URL, responseHandler).execute();
    }

    public Currency changesResponseToCurrency(String response){
        Gson gson = new Gson();
        response = response.replace("?","");
        response = response.replace("(","");
        response = response.replace(")","");
        Log.d(TAG, response.toString());
        return gson.fromJson(response, Currency.class);
    }
}
