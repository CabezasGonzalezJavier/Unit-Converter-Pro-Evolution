package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 18/08/16.
 */
public class GetCommand implements Command{

    private String mUrl;
    private Request mRequest;
    private ResponseHandler mResponseHandler;

    public GetCommand(String url, Request request, ResponseHandler responseHandler) {
        this.mUrl = url;
        this.mRequest = request;
        this.mResponseHandler = responseHandler;
    }

    @Override
    public void execute() {
        mRequest.performGetRequest(mUrl, mResponseHandler);
    }
}
