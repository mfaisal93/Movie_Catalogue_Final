package com.faisal.moviecataloguefinal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviePool {
    @SerializedName("results")
    List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }
}
