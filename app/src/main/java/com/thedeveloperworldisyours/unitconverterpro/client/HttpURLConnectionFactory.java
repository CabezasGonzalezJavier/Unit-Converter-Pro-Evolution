package com.thedeveloperworldisyours.unitconverterpro.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by javierg on 17/08/16.
 */
public class HttpURLConnectionFactory {

    private static HttpURLConnection sHttpURLConnection;
    private static boolean sHasMockitoConnection;

    public static HttpURLConnection getHttpURLConnection(URL url) throws IOException {

        if (!sHasMockitoConnection) {
            sHttpURLConnection = (HttpURLConnection) url.openConnection();
        } else {
            sHasMockitoConnection = false;
        }

        return sHttpURLConnection;
    }

    public static void setHttpURLConnection (HttpURLConnection mockUrlConnection){
        sHttpURLConnection = mockUrlConnection;
        sHasMockitoConnection = true;
    }

}
