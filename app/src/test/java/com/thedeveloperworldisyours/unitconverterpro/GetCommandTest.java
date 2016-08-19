package com.thedeveloperworldisyours.unitconverterpro;

import com.thedeveloperworldisyours.unitconverterpro.interceptor.CurrencyInterceptor;
import com.thedeveloperworldisyours.unitconverterpro.interceptor.CurrencyInterceptorImpl;
import com.thedeveloperworldisyours.unitconverterpro.webservice.CommandFactory;
import com.thedeveloperworldisyours.unitconverterpro.webservice.GetCommand;
import com.thedeveloperworldisyours.unitconverterpro.webservice.Request;
import com.thedeveloperworldisyours.unitconverterpro.webservice.RequestFactory;
import com.thedeveloperworldisyours.unitconverterpro.webservice.RequestImpl;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseHandler;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseImpl;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by javierg on 19/08/16.
 */
public class GetCommandTest {

    private String mURL;
    private ResponseHandler mMockResponseHandler;
    private Request mRequest;

    @Before
    public void setUp(){
        mURL = "some";
        mMockResponseHandler = mock(ResponseHandler.class);
        mRequest = mock(RequestImpl.class);
    }


    @Test
    public void testgetCommandPerfomrsRequest(){

        GetCommand getCommand = new GetCommand(mURL, mRequest, mMockResponseHandler);
        getCommand.execute();
        assertNotNull(mURL);
        assertEquals(mURL, "some");
        verify(mRequest).performGetRequest(mURL, mMockResponseHandler);
    }
}
