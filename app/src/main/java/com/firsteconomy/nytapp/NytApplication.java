package com.firsteconomy.nytapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Gaurav on 30-04-2018.
 */

public class NytApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }

}
