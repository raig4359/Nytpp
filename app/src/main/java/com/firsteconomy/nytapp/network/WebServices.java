package com.firsteconomy.nytapp.network;

import android.arch.lifecycle.LiveData;

import com.firsteconomy.nytapp.network_responses.OverviewBookListResponse;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gaurav on 30-04-2018.
 */

@SuppressWarnings("ALL")
public interface WebServices {

    @GET("topstories/v2/{section}.json")
    Call<TopStoriesResponse> getTopStories_with_Call_returnType(@Path("section") String section);

    @GET("topstories/v2/{section}.json")
    LiveData<ApiResponse<TopStoriesResponse>> getTopStories(@Path("section") String section);

    @GET("books/v3/lists/overview.json")
    LiveData<ApiResponse<OverviewBookListResponse>> getOverviewBookList();
}
