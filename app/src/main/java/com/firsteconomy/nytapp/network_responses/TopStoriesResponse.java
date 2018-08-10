package com.firsteconomy.nytapp.network_responses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firsteconomy.nytapp.model.TopStory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 30-04-2018.
 */

@SuppressWarnings("ALL")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "copyright",
        "section",
        "last_updated",
        "num_results",
        "results"
})
@Entity
public class TopStoriesResponse {

    @JsonProperty("status")
    public String status;

    @Ignore
    @JsonProperty("copyright")
    public String copyright;

    @NonNull
    @PrimaryKey
    @JsonProperty("section")
    public String section;

    @ColumnInfo(name = "last_updated")
    @JsonProperty("last_updated")
    public String lastUpdated;

    @ColumnInfo(name = "num_results")
    @JsonProperty("num_results")
    public int numResults;

    @Ignore
    @JsonProperty("results")
    public ArrayList<TopStory> topStories = null;

    @Override
    public String toString() {
        return "TopStoriesResponse{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", section='" + section + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", numResults=" + numResults +
                ", topStories=" + topStories +
                '}';
    }
}
