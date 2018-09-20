package com.firsteconomy.nytapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        "lists"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookListOverviewResult {
    @JsonProperty("lists")
    public List<BookCategory> bookCategories;

    @Override
    public String toString() {
        return "BookListOverviewResult{" +
                "bookCategories=" + bookCategories +
                '}';
    }
}
