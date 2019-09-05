package com.example.abyandafa.rescueambulance.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abyan Dafa on 05/12/2017.
 */

public class BaseRestService {

    public static Retrofit retrofit;
//    public static String BASE_URL = "http://192.168.43.64/rescue/public/api/";
    public static String BASE_URL = "http://rescueid.picodio.com/rescue/public/api/";
    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();
    public static void setIP(String ip)
    {
        BASE_URL = "http://" + ip +"/rescue/public/api/";
    }

    public static String getIP()
    {
        return BASE_URL;
    }

    public static Retrofit initializeRetrofit() {

        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
