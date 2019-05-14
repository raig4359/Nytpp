package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
        "type",
        "src",
        "width",
        "height"
})

@Entity(foreignKeys = @ForeignKey(
        entity = MovieReview.class,
        parentColumns = "display_title",
        childColumns = "movie"
))
public class MovieMultimedia {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String movie;

    @JsonProperty("type")
    public String type;

    @JsonProperty("src")
    public String src;

    @JsonProperty("width")
    public long width;

    @JsonProperty("height")
    public long height;

}
