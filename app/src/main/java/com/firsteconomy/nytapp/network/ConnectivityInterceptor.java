package com.firsteconomy.nytapp.network;

import android.content.Context;

import com.firsteconomy.nytapp.NytApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gaurav on 21-02-2018.
 */

public class ConnectivityInterceptor implements Interceptor {
    private Context mContext;

    public ConnectivityInterceptor() {
        mContext = NytApplication.getAppContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext)) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
