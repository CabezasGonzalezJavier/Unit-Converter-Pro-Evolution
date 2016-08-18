package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 17/08/16.
 */
public class ResponseFactory {
    public static ResponseImpl createResponse(ResponseHandler responseHandler) {
        return new ResponseImpl(responseHandler);
    }
}
