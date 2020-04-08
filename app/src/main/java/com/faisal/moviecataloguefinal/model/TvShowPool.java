package com.faisal.moviecataloguefinal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowPool {
    @SerializedName("results")
    List<TvShow> tvShows;

    public List<TvShow> getTvShowsList() {
        return tvShows;
    }
}
