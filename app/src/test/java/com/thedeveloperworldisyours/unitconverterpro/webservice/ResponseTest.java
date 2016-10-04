package com.thedeveloperworldisyours.unitconverterpro.webservice;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by javierg on 19/08/16.
 */
public class ResponseTest {

    private String mResponse;
    private String mResponseError;
    private String mResponseGeneralError;
    private ResponseListener mMockResponseListener;
    private ResponseHandler mMockResponseHandler;

    @Before
    public void setUp() {
        mResponse = "response";
        mResponseGeneralError = "GeneralError";
        mResponseError = "Error";
        mMockResponseHandler = mock(ResponseHandler.class);
        mMockResponseListener = new ResponseImpl(mMockResponseHandler);
    }

    @Test
    public void successfulResponseTest() {
        mMockResponseListener.onSuccess(mResponse);

        assertNotNull(mResponse);
        assertTrue(mResponse.equals("response"));
        verify(mMockResponseHandler).sendResponseSuccessful(mResponse);
    }

    @Test
    public void generalErrorResponseTest(){
        mMockResponseListener.onGeneralError();

        assertNotNull(mResponseGeneralError);
        assertEquals(mResponseGeneralError, "GeneralError");
        verify(mMockResponseHandler).sendResponseGeneralError();
    }

    @Test
    public void errorResponseTest() {
        mMockResponseListener.onError(mResponseError);
        assertNotNull(mResponseError);
        assertEquals(mResponseError, "Error");
        verify(mMockResponseHandler).sendResponseFail(mResponseError);
    }
}
