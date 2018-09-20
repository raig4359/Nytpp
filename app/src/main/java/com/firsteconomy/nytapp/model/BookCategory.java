package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by Gaurav on 04-09-2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "list_id",
        "list_name",
        "list_name_encoded",
        "display_name",
        "books"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class BookCategory {

    @PrimaryKey
    @ColumnInfo(name = "list_id")
    @JsonProperty("list_id")
    public long listId;

    @JsonProperty("list_name")
    public String listName;

    @JsonProperty("list_name_encoded")
    public String listNameEncoded;

    @JsonProperty("display_name")
    public String displayName;

    @Ignore
    @JsonProperty("books")
    public List<Book> books;

    @Override
    public String toString() {
        return "BookCategory{" +
                "listId=" + listId +
                ", listName='" + listName + '\'' +
                ", listNameEncoded='" + listNameEncoded + '\'' +
                ", displayName='" + displayName + '\'' +
                ", books=" + books +
                '}';
    }
}
