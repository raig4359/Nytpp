package com.firsteconomy.nytapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.db.DBManager;
import com.firsteconomy.nytapp.db.StoryDao;
import com.firsteconomy.nytapp.model.AllStoryDataCombined;
import com.firsteconomy.nytapp.model.Multimedium;
import com.firsteconomy.nytapp.model.StoryWithMedia;
import com.firsteconomy.nytapp.model.TopStory;
import com.firsteconomy.nytapp.network.ApiResponse;
import com.firsteconomy.nytapp.network.NetworkBoundResource;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network.RestClient;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gaurav on 28-05-2018.
 */

public class TopStoryRepository {

    Context context;
    private String TAG = "TopStoryRepo";

    public TopStoryRepository(Context context) {
        this.context = context;
    }

    public LiveData<Resource<TopStoriesResponse>> loadTopStories(final String section) {
        return new NetworkBoundResource<TopStoriesResponse, TopStoriesResponse>() {

            @Override
            protected void saveCallResult(TopStoriesResponse item) {
                StoryDao storyDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .storyDao();

                List<Multimedium> multimediumList = new ArrayList<>();

                for (TopStory topStory : item.topStories) {
                    topStory.global_section = item.section;
                    if (topStory.shortUrl == null) {
                        topStory.shortUrl = getRandomString(12);
                    }
                    for (Multimedium multimedium : topStory.multimedia) {
                        multimedium.storyUrl = topStory.shortUrl;
                    }
                    multimediumList.addAll(topStory.multimedia);
                }

                storyDao.insertTopStoriesResponse(item);
                storyDao.insertTopStories(item.topStories);
                storyDao.insertMultimedium(multimediumList);

            }

            @Override
            protected boolean shouldFetch(TopStoriesResponse item) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<TopStoriesResponse> loadFromDb() {

                final StoryDao storyDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .storyDao();

                final MutableLiveData<TopStoriesResponse> data = new MutableLiveData<>();

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        final List<AllStoryDataCombined> stories = storyDao.getTopStories(section);

                        final List<StoryWithMedia> multiMedia = storyDao.getStoryWithMedia(section);


                        ArrayList<TopStory> topStories = new ArrayList<>();

                        for (StoryWithMedia storyWithMedia : multiMedia) {
                            TopStory topStory = storyWithMedia.story;
                            topStory.multimedia = storyWithMedia.multimedia;
                            topStories.add(topStory);
                        }

                        if (stories.size() != 0) {
                            stories.get(0).response.topStories = topStories;
                            data.postValue(stories.get(0).response);
                        } else {
                            data.postValue(null);
                        }
                    }
                });

                return data;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<TopStoriesResponse>> createCall() {
                return RestClient.webServices().getTopStories(section);
            }
        }.getAsLiveData();
    }

    public static String getRandomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}

