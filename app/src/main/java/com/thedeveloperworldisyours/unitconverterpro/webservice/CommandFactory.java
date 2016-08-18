package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 18/08/16.
 */
public class CommandFactory {
    public static GetCommand createGetCommand(String url, ResponseHandler handler){
        Request request = RequestFactory.createRequestImpl();
        return new GetCommand(url, request, handler);
    }
}
