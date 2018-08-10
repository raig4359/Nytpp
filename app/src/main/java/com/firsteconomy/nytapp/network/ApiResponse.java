package com.firsteconomy.nytapp.network;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Gaurav on 28-05-2018.
 */

@SuppressWarnings("ALL")
public class ApiResponse<T> {

    public final int code;
    public final T body;
    public final String errorMessage;

    public ApiResponse(Throwable error) {
        if (error instanceof NoConnectivityException) {
            code = 501;
        } else {
            code = 500;
        }
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResponse(Response<T> response) {
        this.code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

    public boolean isInternetConnectionError() {
        return code == 501;
    }

}
