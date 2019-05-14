package com.firsteconomy.nytapp.ui.test_stuff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firsteconomy.nytapp.network.RestClient;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import java.io.IOException;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import retrofit2.Response;

/**
 * Created by Gaurav on 22-06-2018.
 */

public class SampleWorker extends Worker {

    private String TAG ="SampleWorker";

    public SampleWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Response<TopStoriesResponse> response = null;

        Log.e(TAG, "doWork: work started..." );

        try {
            response = RestClient.webServices()
                    .getTopStories_with_Call_returnType("home")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null || response.body() == null) {
            return Result.retry();
        }

        if (response.body().status.equalsIgnoreCase("OK")) {
            return Result.success();
        }

        return Result.failure();
    }




}
