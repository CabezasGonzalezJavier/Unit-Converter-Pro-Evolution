package com.thedeveloperworldisyours.unitconverterpro.webservice;

/**
 * Created by javierg on 17/08/16.
 */
public class ResponseImpl implements ResponseListener {

    private ResponseHandler mResponseHandler;

    public ResponseImpl(ResponseHandler mResponseHandler) {
        this.mResponseHandler = mResponseHandler;
    }

    @Override
    public void onSuccess(String successResponse) {
        mResponseHandler.sendResponseSuccessful(successResponse);
    }

    @Override
    public void onError(String errorResponse) {
        mResponseHandler.sendResponseFail(errorResponse);
    }

    @Override
    public void onGeneralError() {
        mResponseHandler.sendResponseGeneralError();
    }
}
