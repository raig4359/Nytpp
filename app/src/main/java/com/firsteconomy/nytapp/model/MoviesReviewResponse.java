package com.firsteconomy.nytapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by Gaurav on 13-12-2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "copyright",
        "has_more",
        "num_results",
        "results"
})
public class MoviesReviewResponse {

    @JsonProperty("status")
    public String status;

    @JsonProperty("copyright")
    public String copyright;

    @JsonProperty("has_more")
    public boolean hasMore;

    @JsonProperty("num_results")
    public long numResults;

    @JsonProperty("results")
    public List<MovieReview> results = null;

}
