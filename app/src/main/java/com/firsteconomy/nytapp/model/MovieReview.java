package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Gaurav on 12-12-2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "display_title",
        "mpaa_rating",
        "critics_pick",
        "byline",
        "headline",
        "summary_short",
        "publication_date",
        "opening_date",
        "date_updated",
        "link",
        "multimedia"
})
@Entity
public class MovieReview {

    @NonNull
    @PrimaryKey
    @JsonProperty("display_title")
    @ColumnInfo(name = "display_title")
    public String displayTitle = "";

    @JsonProperty("mpaa_rating")
    public String rating;

    @JsonProperty("critics_pick")
    public long criticsPick;

    @JsonProperty("byline")
    public String byline;

    @JsonProperty("headline")
    public String headline;

    @JsonProperty("summary_short")
    public String summaryShort;

    @JsonProperty("publication_date")
    public String publicationDate;

    @JsonProperty("opening_date")
    public String openingDate;

    @JsonProperty("date_updated")
    public String dateUpdated;

    @Ignore
    @JsonProperty("multimedia")
    public MovieMultimedia multimedia;

}
