package com.thedeveloperworldisyours.unitconverterpro.client;

import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseListener;

/**
 * Created by javierg on 17/08/16.
 */
public class ClientHTTPImpl implements ClientHTTP{

    ResponseListener mResponseListener;

    ClientAsyncTask mClientAsyncTask;

    public ClientHTTPImpl(ResponseListener responseListener) {
        this.mResponseListener = responseListener;
    }

    @Override
    public void get(String url) {
        mClientAsyncTask = new ClientAsyncTaskImpl();
        mClientAsyncTask.executeAsync(mResponseListener, url, "GET");
    }

    @Override
    public void post(String url) {

    }

    @Override
    public void put(String url) {

    }

    @Override
    public void delete(String url) {

    }
}
