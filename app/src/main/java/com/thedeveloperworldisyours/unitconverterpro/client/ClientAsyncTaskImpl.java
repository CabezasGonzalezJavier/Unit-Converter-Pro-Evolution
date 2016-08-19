package com.thedeveloperworldisyours.unitconverterpro.client;

import android.os.AsyncTask;
import android.util.Log;

import com.thedeveloperworldisyours.unitconverterpro.model.Response;
import com.thedeveloperworldisyours.unitconverterpro.webservice.ResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by javierg on 17/08/16.
 */
public class ClientAsyncTaskImpl extends AsyncTask<String, Void, Response> implements ClientAsyncTask{

    private ResponseListener mResponseListener;

    @Override
    public void executeAsync(ResponseListener listener, String url, String type) {

        mResponseListener = listener;
        this.execute(url, type);
    }

    @Override
    protected Response doInBackground(String... params) {
        final String url = params[0];
        final String type = params[1];

        return callClient(url, type);
    }

    public Response callClient(final String url, final String type) {

        Response responseModel = new Response();
        String responseJson;

        try {
            URL obj = new URL(url);

            HttpURLConnection connection = HttpURLConnectionFactory.getHttpURLConnection(obj);

            connection.setRequestMethod(type);


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseJson = response.toString();

//            Log.v("Asyntask", String.valueOf(con.getResponseCode()));

            //con.getResponseCode() //set statusCode to MovieInterceptor obj
            responseModel.setContent(connection.getContent()); // set the body to the T response
            responseModel.setHeaders(connection.getHeaderFields());
            responseModel.setCode(connection.getResponseCode());
            responseModel.setInfo(responseJson);
//            Log.v("Client",con.getResponseCode());
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
            responseJson = e.toString();
            responseModel.setInternetError(true);

            mResponseListener.onError(e.toString());
        }

        return responseModel;
    }

    @Override
    protected void onPostExecute(Response response) {

        if (response.isInternetError()) {
            mResponseListener.onGeneralError();
        } else {

            if (response.checkStatusCode(response.getCode())) {
                mResponseListener.onSuccess(response.getInfo());
            } else {
                mResponseListener.onError(response.getInfo());
            }
        }
    }

}
