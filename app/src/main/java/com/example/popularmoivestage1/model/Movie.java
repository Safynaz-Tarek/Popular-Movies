package com.example.popularmoivestage1.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private String plotOverview;
    private String movieTitle;
    private Double rating;
    private String date;
    private String image;
    final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    public  Movie(){

    }

    public Movie(String plotOverview, String movieTitle, Double rating, String date, String image) {
        this.plotOverview = plotOverview;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.date = date;
        this.image = image;
    }

    public String getPlotOverview() { return plotOverview; }
    public String getMovieTitle() { return movieTitle; }
    public Double getRating() { return rating; }
    public String getDate() { return date; }
    public String getImage() { return image; }

    public void setPlotOverview(String plotOverview) { this.plotOverview = plotOverview; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setDate(String date) { this.date = date; }
    public void setImage(String image) { this.image = POSTER_BASE_URL + image; }
}
