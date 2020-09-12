package org.sayar.mobisoftdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Navid Mahboubi at 9/8/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MovieEntity extends RealmObject {

//    private int id;
    @PrimaryKey
    private String imdbID;
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Rated")
    private String rated;
    @SerializedName("Released")
    private String released;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Director")
    private String director;
    @SerializedName("Writer")
    private String writer;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Language")
    private String language;
    @SerializedName("Country")
    private String country;
    @SerializedName("Awards")
    private String awards;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Ratings")
    private RealmList<MovieRateEntity> ratings;
    @SerializedName("Metascore")
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    @SerializedName("Type")
    private String type;
    @SerializedName("DVD")
    private String dVD;
    @SerializedName("BoxOffice")
    private String boxOffice;
    @SerializedName("Production")
    private String production;
    @SerializedName("Website")
    private String website;
    @SerializedName("Response")
    private String response;

    public MovieEntity() {
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public RealmList<MovieRateEntity> getRatings() {
        return ratings;
    }

    public void setRatings(RealmList<MovieRateEntity> ratings) {
        this.ratings = ratings;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getdVD() {
        return dVD;
    }

    public void setdVD(String dVD) {
        this.dVD = dVD;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
