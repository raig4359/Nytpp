package com.firsteconomy.nytapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.firsteconomy.nytapp.model.MovieInfoDetail;
import com.firsteconomy.nytapp.model.MovieMultimedia;
import com.firsteconomy.nytapp.model.MovieReview;

import java.util.List;

/**
 * Created by Gaurav on 12-12-2018.
 */
@Dao
public interface MovieReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovieReview(MovieReview... reviews);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovieReview(List<MovieReview> reviews);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovieMultimedia(List<MovieMultimedia> multimedia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovieMultimedia(MovieMultimedia... multimedia);

    @Query("SELECT * FROM MovieReview")
    List<MovieInfoDetail> getMoviesInfo();

}
