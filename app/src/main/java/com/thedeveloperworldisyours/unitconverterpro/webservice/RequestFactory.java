package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 18/08/16.
 */
public class RequestFactory {

    private static RequestImpl sRequestImpl;

    public static RequestImpl createRequestImpl(){
        if (sRequestImpl == null) {
            sRequestImpl = new RequestImpl();
        }

        return sRequestImpl;
    }

    public static void setMockRequesImpl(RequestImpl requestImpl) {
        sRequestImpl = requestImpl;
    }
}
