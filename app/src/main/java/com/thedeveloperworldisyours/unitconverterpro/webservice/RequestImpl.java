package com.thedeveloperworldisyours.unitconverterpro.webservice;

import com.thedeveloperworldisyours.unitconverterpro.client.ClientHTTP;
import com.thedeveloperworldisyours.unitconverterpro.client.ClientHTTPFactory;

/**
 * Created by javierg on 18/08/16.
 */
public class RequestImpl implements Request {


    @Override
    public void performGetRequest(String url, ResponseHandler responseHandler) {
        ResponseListener responseListener = ResponseFactory.createResponse(responseHandler);
        ClientHTTP clientHTTP = ClientHTTPFactory.createClientHttp(responseListener);
        clientHTTP.get(url);

    }

    @Override
    public void performPutRequest(String url, ResponseHandler responseHandler) {

    }

    @Override
    public void performPostRequest(String url, ResponseHandler responseHandler) {

    }

    @Override
    public void performDeleteRequest(String url, ResponseHandler responseHandler) {

    }
}
