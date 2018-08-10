package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

/**
 * Created by Gaurav on 30-04-2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url",
        "format",
        "height",
        "width",
        "type",
        "subtype",
        "caption",
        "copyright"
})
@Entity(foreignKeys = @ForeignKey(
        entity = TopStory.class,
        parentColumns = "short_url",
        childColumns = "story_url"
))
public class Multimedium {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @JsonProperty("url")
    public String url;

    @JsonProperty("format")
    public String format;

    @JsonProperty("height")
    public int height;

    @JsonProperty("width")
    public int width;

    @JsonProperty("type")
    public String type;

    @JsonProperty("subtype")
    public String subtype;

    @JsonProperty("caption")
    public String caption;

    @Ignore
    @JsonProperty("copyright")
    public String copyright;

    @ColumnInfo(name = "story_id")
    public long storyId;

    @ColumnInfo(name = "story_url")
    public String storyUrl;

}
