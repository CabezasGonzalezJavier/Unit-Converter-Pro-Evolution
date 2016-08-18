package com.thedeveloperworldisyours.unitconverterpro.client;

import com.thedeveloperworldisyours.unitconverterpro.model.Response;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseListener;

/**
 * Created by javierg on 17/08/16.
 */
public interface ClientAsyncTask {
    public void executeAsync(ResponseListener mListener, String url, String type);
    public Response callClient(final String url, final String type);
}