package com.firsteconomy.nytapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.db.DBManager;
import com.firsteconomy.nytapp.db.MovieReviewDao;
import com.firsteconomy.nytapp.model.MovieInfoDetail;
import com.firsteconomy.nytapp.model.MovieMultimedia;
import com.firsteconomy.nytapp.model.MovieReview;
import com.firsteconomy.nytapp.model.MoviesReviewResponse;
import com.firsteconomy.nytapp.network.ApiResponse;
import com.firsteconomy.nytapp.network.NetworkBoundResource;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gaurav on 12-12-2018.
 */
public class MovieReviewRepository {

    Context context;
    private String TAG = "MovieReviewRepo";

    public MovieReviewRepository(Context context) {
        this.context = context;
    }

    public LiveData<Resource<List<MovieReview>>> getMovieReviews() {
        return new NetworkBoundResource<List<MovieReview>, MoviesReviewResponse>() {

            @Override
            protected void saveCallResult(MoviesReviewResponse item) {
                MovieReviewDao movieDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .movieReviewDao();
                ArrayList<MovieMultimedia> multimediaList = new ArrayList<>();
                for (MovieReview review : item.results) {
                    multimediaList.add(review.multimedia);
                }
                movieDao.insertMovieReview(item.results);
                movieDao.insertMovieMultimedia(multimediaList);
            }

            @Override
            protected boolean shouldFetch(List<MovieReview> item) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<MovieReview>> loadFromDb() {
                final MovieReviewDao reviewDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .movieReviewDao();

                final MutableLiveData<List<MovieReview>> data = new MutableLiveData<>();

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<MovieInfoDetail> reviewInfoDetail = reviewDao.getMoviesInfo();

                        List<MovieReview> reviews = new ArrayList<>();

                        for (MovieInfoDetail detail : reviewInfoDetail) {
                            if (detail.multimedia.size() > 0) {
                                detail.review.multimedia = detail.multimedia.get(0);
                            }
                            reviews.add(detail.review);
                        }
                        data.postValue(reviews);
                    }
                });

                return data;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MoviesReviewResponse>> createCall() {
                return RestClient.webServices().getMovieReviews();
            }
        }.getAsLiveData();
    }
}
