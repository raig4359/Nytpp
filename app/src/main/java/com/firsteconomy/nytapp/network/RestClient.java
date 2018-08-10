package com.firsteconomy.nytapp.network;


import com.firsteconomy.nytapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Gaurav on 21-02-2018.
 */

public class RestClient {

    String BASE_URL = "http://dev.api.winds.co.in/api/v1/";

    private static RestClient restClient;
    private static ConnectivityInterceptor connectivityInterceptor;
    private static HeaderInterceptor headerInterceptor;

    public static WebServices webServices() {
        return getRestClient().getRetrofitInstance().create(WebServices.class);
    }

    private static RestClient getRestClient() {
        if (restClient == null) {
            restClient = new RestClient();
            connectivityInterceptor = new ConnectivityInterceptor();
            headerInterceptor = new HeaderInterceptor();
        }
        return restClient;
    }

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(getOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(headerInterceptor)
                .build();
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static void destroyRestClient() {
        connectivityInterceptor = null;
        restClient = null;
    }
}

