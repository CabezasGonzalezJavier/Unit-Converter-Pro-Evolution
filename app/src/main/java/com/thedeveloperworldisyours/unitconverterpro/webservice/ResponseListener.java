package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 17/08/16.
 */
public interface ResponseListener {

    public void onSuccess(final String successResponse);
    public void onError(final String errorResponse);
    public void onGeneralError();

}
