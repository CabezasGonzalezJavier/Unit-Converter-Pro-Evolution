package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 17/08/16.
 */
public interface ResponseHandler {

    public void sendResponseSuccessful(String response);
    public void sendResponseFail(String error);
    public void sendResponseGeneralError();

}
