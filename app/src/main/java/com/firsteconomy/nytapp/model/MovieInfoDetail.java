package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;


/**
 * Created by Gaurav on 13-12-2018.
 */
public class MovieInfoDetail {

    @Embedded
    public MovieReview review;

    @Relation(parentColumn = "display_title", entityColumn = "movie")
    public List<MovieMultimedia> multimedia;

}
