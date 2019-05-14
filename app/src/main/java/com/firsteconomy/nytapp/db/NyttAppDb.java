package com.firsteconomy.nytapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.firsteconomy.nytapp.model.Book;
import com.firsteconomy.nytapp.model.BookCategory;
import com.firsteconomy.nytapp.model.MovieMultimedia;
import com.firsteconomy.nytapp.model.MovieReview;
import com.firsteconomy.nytapp.model.Multimedium;
import com.firsteconomy.nytapp.model.TopStory;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

@SuppressWarnings("ALL")
@Database(entities = {
        TopStoriesResponse.class,
        TopStory.class,
        Multimedium.class,
        BookCategory.class,
        Book.class,
        MovieReview.class,
        MovieMultimedia.class}, version = 1)
public abstract class NyttAppDb extends RoomDatabase {
    public abstract StoryDao storyDao();
    public abstract BookDao bookDao();
    public abstract MovieReviewDao movieReviewDao();
}
