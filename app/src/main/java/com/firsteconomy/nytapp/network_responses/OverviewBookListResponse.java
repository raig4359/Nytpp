package com.firsteconomy.nytapp.network_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firsteconomy.nytapp.model.BookListOverviewResult;

/**
 * Created by Gaurav on 04-09-2018.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "copyright",
        "num_results",
        "results"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverviewBookListResponse {

    @JsonProperty("status")
    public String status;
    @JsonProperty("copyright")
    public String copyright;
    @JsonProperty("num_results")
    public long numResults;
    @JsonProperty("results")
    public BookListOverviewResult results;

    @Override
    public String toString() {
        return "OverviewBookListResponse{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", numResults=" + numResults +
                ", results=" + results +
                '}';
    }
}
