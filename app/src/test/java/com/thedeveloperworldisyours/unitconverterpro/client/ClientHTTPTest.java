package com.thedeveloperworldisyours.unitconverterpro.client;

import com.thedeveloperworldisyours.unitconverterpro.common.utils.Constant;
import com.thedeveloperworldisyours.unitconverterpro.model.Response;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by javierg on 19/08/16.
 */
public class ClientHTTPTest {

    private HttpURLConnection mockConnection;
    private String mURL, mType;
    private ClientAsyncTask mClientAsyncTask;

    @Before
    public void setUp() {
        mClientAsyncTask = new ClientAsyncTaskImpl();
        mURL = Constant.URL;
        mType = "GET";
    }

    @Test
    public void setClientHttpWithOnPostExecuteSuccess(){
        mockConnection = mock(HttpURLConnection.class);

        try {
            when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream("Hello".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpURLConnectionFactory.setHttpURLConnection(mockConnection);

        Response response = mClientAsyncTask.callClient(mURL, mType);

        try {
            verify(mockConnection).setRequestMethod(mType);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        assertNotNull(response);
        assertFalse(response.isInternetError());
        assertTrue(response.getInfo() != null);
        assertTrue(response.getInfo().equals("Hello"));
    }

    @Test
    public void setClientHttpWithOnPostExecuteException(){
        mockConnection = mock(HttpURLConnection.class);

        try {
            when(mockConnection.getInputStream()).thenThrow(new IOException());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpURLConnectionFactory.setHttpURLConnection(mockConnection);

        Response response = mClientAsyncTask.callClient(mURL, mType);
        assertNotNull(response);
        assertTrue(response.isInternetError());
        assertTrue(response.getInfo() == null);
    }
}
