package org.sayar.mobisoftdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Navid Mahboubi at 9/8/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MovieSearchResponse implements Serializable {

    @SerializedName("Search")
    private List<MovieEntity> search;
    private String totalResults;
    @SerializedName("Response")
    private String response;

    public MovieSearchResponse() {
    }

    public List<MovieEntity> getSearch() {
        return search;
    }

    public void setSearch(List<MovieEntity> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
