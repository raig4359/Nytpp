package com.firsteconomy.nytapp.ui.top_stories;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.model.TopStory;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;
import com.firsteconomy.nytapp.repository.TopStoryRepository;
import com.firsteconomy.nytapp.util.AbsentLiveData;

import java.util.ArrayList;

/**
 * Created by Gaurav on 08-05-2018.
 */

@SuppressWarnings("ALL")
public class TopStoryViewModel extends ViewModel {

    private TopStoryRepository topStoryRepository;
    private MutableLiveData<String> section;
    private LiveData<Resource<TopStoriesResponse>> topStoryData;

    public TopStoryViewModel(final TopStoryRepository topStoryRepository) {
        this.topStoryRepository = topStoryRepository;
        section = new MutableLiveData<>();
        topStoryData = Transformations.switchMap(section, new Function<String, LiveData<Resource<TopStoriesResponse>>>() {
            @Override
            public LiveData<Resource<TopStoriesResponse>> apply(String section) {
                if (section == null || section.isEmpty()) {
                    return AbsentLiveData.create();
                } else {
                    return topStoryRepository.loadTopStories(section);
                }
            }
        });
    }

    public void setSection(String section) {
        this.section.setValue(section);
    }

    public LiveData<Resource<TopStoriesResponse>> getTopStoryData() {
        return topStoryData;
    }

    public void retry(){
        String sectionValue = section.getValue();
        if (sectionValue != null && !sectionValue.isEmpty()) {
            section.setValue(sectionValue);
        }
    }

    public TopStoryRepository getTopStoryRepository() {
        return topStoryRepository;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final Context context;
        private final TopStoryRepository topStoryRepository;

        public Factory(Context context) {
            this.context = context;
            topStoryRepository = new TopStoryRepository(context);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TopStoryViewModel(topStoryRepository);
        }
    }

}
