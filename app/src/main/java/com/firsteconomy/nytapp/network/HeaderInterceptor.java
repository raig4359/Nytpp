package com.firsteconomy.nytapp.network;

import com.firsteconomy.nytapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gaurav on 04-05-2018.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("api-key", BuildConfig.API_KEY)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
