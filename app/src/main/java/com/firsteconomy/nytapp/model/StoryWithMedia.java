package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class StoryWithMedia {

    @Embedded
    public TopStory story;

    @Relation(parentColumn = "short_url", entityColumn = "story_url")
    public List<Multimedium> multimedia;

}
