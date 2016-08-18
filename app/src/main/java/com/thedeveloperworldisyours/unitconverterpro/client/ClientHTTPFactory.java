package com.thedeveloperworldisyours.unitconverterpro.client;

import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseListener;

/**
 * Created by javierg on 18/08/16.
 */
public class ClientHTTPFactory {
    private static ClientHTTP sClientHTTP;
    private static boolean sConnection;

    public static ClientHTTP createClientHttp(ResponseListener responseListener) {

        if (!sConnection){
            sClientHTTP = new ClientHTTPImpl(responseListener);
        } else {
            sConnection = false;
        }

        return sClientHTTP;
    }

    public static void setMockClientHTTP(ClientHTTP clientHTTP){
        sClientHTTP = clientHTTP;
        sConnection = true;
    }
}
