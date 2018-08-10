package com.firsteconomy.nytapp.util;

import android.arch.lifecycle.LiveData;

@SuppressWarnings("ALL")
public class AbsentLiveData extends LiveData {

    private AbsentLiveData() {
        postValue(null);
    }

    public static <T> LiveData<T> create(){
        return new AbsentLiveData();
    }

}
