package com.thedeveloperworldisyours.unitconverterpro;

import com.thedeveloperworldisyours.unitconverterpro.client.ClientHTTP;
import com.thedeveloperworldisyours.unitconverterpro.client.ClientHTTPFactory;
import com.thedeveloperworldisyours.unitconverterpro.webservice.Request;
import com.thedeveloperworldisyours.unitconverterpro.webservice.RequestFactory;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseHandler;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by javierg on 19/08/16.
 */
public class RequestTest {

    private String mURL;
    private Request mRequest;
    private ResponseHandler mMockResponseHandler;
    private ClientHTTP mMockClientHTTP;

    @Before
    public void setUp() {
        mRequest = RequestFactory.createRequestImpl();
        mURL = "SomeURL";
        mMockResponseHandler = mock(ResponseHandler.class);
        mMockClientHTTP = mock(ClientHTTP.class);

        ClientHTTPFactory.setMockClientHTTP(mMockClientHTTP);
    }

    @Test
    public void requestSuccessful(){
        mRequest.performGetRequest(mURL, mMockResponseHandler);
        verify(mMockClientHTTP).get(mURL);
    }
}
