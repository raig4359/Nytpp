package com.firsteconomy.nytapp.network;

import java.io.IOException;

/**
 * Created by Gaurav on 11-04-2018.
 */

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
