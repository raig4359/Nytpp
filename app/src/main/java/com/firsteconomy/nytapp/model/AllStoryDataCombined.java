package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import java.util.List;

public class AllStoryDataCombined {

    @Embedded
    public TopStoriesResponse response;

    @Relation(parentColumn = "section", entityColumn = "global_section")
    public List<TopStory> topStories;

}
