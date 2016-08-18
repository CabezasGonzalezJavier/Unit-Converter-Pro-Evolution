package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 18/08/16.
 */
public interface Request {
    public void performGetRequest(String url, ResponseHandler responseHandler);
    public void performPutRequest(String url, ResponseHandler responseHandler);
    public void performPostRequest(String url, ResponseHandler responseHandler);
    public void performDeleteRequest(String url, ResponseHandler responseHandler);
}
