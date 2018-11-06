package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Gaurav on 04-09-2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "author",
        "book_image",
        "contributor",
        "description",
        "price",
        "publisher",
        "rank",
        "title"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(foreignKeys = @ForeignKey(
        entity = BookCategory.class,
        parentColumns = "list_id",
        childColumns = "category_id"
))
public class Book {

    @JsonIgnore
    @ColumnInfo(name = "category_id")
    public long categoryId;

    @JsonProperty("author")
    public String author;

    @JsonProperty("book_image")
    public String bookImage;

    @JsonProperty("contributor")
    public String contributor;

    @JsonProperty("description")
    public String description;

    @JsonProperty("price")
    public long price;

    @JsonProperty("publisher")
    public String publisher;

    @JsonProperty("rank")
    public long rank;

    @NonNull
    @PrimaryKey
    @JsonProperty("title")
    public String title;

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookImage='" + bookImage + '\'' +
                ", contributor='" + contributor + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                '}';
    }
}
