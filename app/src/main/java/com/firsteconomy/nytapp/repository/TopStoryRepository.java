package com.firsteconomy.nytapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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

                final MediatorLiveData<TopStoriesResponse> data = new MediatorLiveData<>();

                final LiveData<List<AllStoryDataCombined>> storyLiveData = storyDao.getTopStories(section);

                final LiveData<List<StoryWithMedia>> storyDaoMultiMedia = storyDao.getStoryWithMedia(section);

                data.addSource(storyLiveData, new Observer<List<AllStoryDataCombined>>() {
                    @Override
                    public void onChanged(@Nullable final List<AllStoryDataCombined> dataCombined) {

                        Log.e(TAG, "onChanged: section - " + section + ", data hashcode -  " + data.hashCode() +
                                ", story multimedia hashcode - " + storyDaoMultiMedia.hashCode());

                        data.addSource(storyDaoMultiMedia, new Observer<List<StoryWithMedia>>() {
                            @Override
                            public void onChanged(@Nullable List<StoryWithMedia> storyList) {

                                ArrayList<TopStory> topStories = new ArrayList<>();

                                for (StoryWithMedia storyWithMedia : storyList) {
                                    TopStory topStory = storyWithMedia.story;
                                    topStory.multimedia = storyWithMedia.multimedia;
                                    topStories.add(topStory);
                                }

                                if (dataCombined.size() != 0) {
                                    dataCombined.get(0).response.topStories = topStories;
                                    data.setValue(dataCombined.get(0).response);
                                } else {
                                    data.setValue(null);
                                }

                                data.removeSource(storyLiveData);
                                data.removeSource(storyDaoMultiMedia);

                            }
                        });

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

