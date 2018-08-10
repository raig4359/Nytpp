package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firsteconomy.nytapp.network_responses.TopStoriesResponse;

import java.util.List;

/**
 * Created by Gaurav on 30-04-2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "section",
        "subsection",
        "title",
        "abstract",
        "url",
        "byline",
        "item_type",
        "updated_date",
        "created_date",
        "published_date",
        "multimedia",
        "short_url"
})
@SuppressWarnings("ALL")
@Entity(foreignKeys = @ForeignKey(
        entity = TopStoriesResponse.class,
        parentColumns = "section",
        childColumns = "global_section"))
public class TopStory {

    @JsonProperty("section")
    public String section;

    @JsonProperty("subsection")
    public String subsection;

    @JsonProperty("title")
    public String title;

    @JsonProperty("abstract")
    public String _abstract;

    @JsonProperty("url")
    public String url;

    @JsonProperty("byline")
    public String byline;

    @JsonProperty("item_type")
    public String itemType;

    @JsonProperty("updated_date")
    public String updatedDate;

    @JsonProperty("created_date")
    public String createdDate;

    @JsonProperty("published_date")
    public String publishedDate;

    @Ignore
    @JsonProperty("multimedia")
    public List<Multimedium> multimedia = null;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "short_url")
    @JsonProperty("short_url")
    public String shortUrl;

    @ColumnInfo(name = "global_section")
    public String global_section;

    @Override
    public String toString() {
        return "TopStory{" +
                "section='" + section + '\'' +
                ", subsection='" + subsection + '\'' +
                ", title='" + title + '\'' +
                ", _abstract='" + _abstract + '\'' +
                ", url='" + url + '\'' +
                ", byline='" + byline + '\'' +
                ", itemType='" + itemType + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", multimedia=" + multimedia +
                ", global_section='" + global_section + '\'' +
                '}';
    }
}
