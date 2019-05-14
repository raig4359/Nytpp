package com.firsteconomy.nytapp.ui.movie_review;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.model.MovieReview;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.repository.MovieReviewRepository;

import java.util.List;

/**
 * Created by Gaurav on 12-12-2018.
 */
public class MovieReviewViewModel extends ViewModel {
    private MovieReviewRepository movieRepository;

    public MovieReviewViewModel(final MovieReviewRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<Resource<List<MovieReview>>> getReviewList() {
        return movieRepository.getMovieReviews();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final Context context;
        private final MovieReviewRepository movieRepository;

        public Factory(Context context) {
            this.context = context;
            this.movieRepository = new MovieReviewRepository(context);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MovieReviewViewModel(movieRepository);
        }
    }
}
