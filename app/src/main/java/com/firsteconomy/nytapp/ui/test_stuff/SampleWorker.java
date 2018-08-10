package com.firsteconomy.nytapp.ui.test_stuff;

import android.support.annotation.NonNull;
import android.util.Log;

import com.firsteconomy.nytapp.network.RestClient;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import java.io.IOException;

import androidx.work.Worker;
import retrofit2.Response;

/**
 * Created by Gaurav on 22-06-2018.
 */

public class SampleWorker extends Worker {

    private String TAG ="SampleWorker";

    @NonNull
    @Override
    public WorkerResult doWork() {

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
            return WorkerResult.RETRY;
        }

        if (response.body().status.equalsIgnoreCase("OK")) {
            return WorkerResult.SUCCESS;
        }

        return WorkerResult.FAILURE;
    }


}
