package com.thedeveloperworldisyours.unitconverterpro.client;

/**
 * Created by javierg on 17/08/16.
 */
public interface ClientHTTP {
    public void get(String url);
    public void post(String url);
    public void put(String url);
    public void delete(String url);
}
