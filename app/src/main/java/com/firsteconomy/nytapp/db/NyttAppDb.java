package com.firsteconomy.nytapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.firsteconomy.nytapp.model.Multimedium;
import com.firsteconomy.nytapp.model.TopStory;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

@SuppressWarnings("ALL")
@Database(entities = {
        TopStoriesResponse.class,
        TopStory.class,
        Multimedium.class}, version = 1)
public abstract class NyttAppDb extends RoomDatabase {
    public abstract StoryDao storyDao();
}
