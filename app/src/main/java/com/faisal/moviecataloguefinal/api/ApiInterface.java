package com.faisal.moviecataloguefinal.api;

import com.faisal.moviecataloguefinal.model.MoviePool;
import com.faisal.moviecataloguefinal.model.TvShowPool;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @GET("discover/movie/?api_key=6bb3ab771fecb31508a3a12094d5f4b3&language=en-US")
    Call<MoviePool> listMovies(@Query("movie") String m);

    @GET("discover/tv/?api_key=6bb3ab771fecb31508a3a12094d5f4b3&language=en-US")
    Call<TvShowPool> listTv(@Query("tv") String t);

    @GET("search/movie?api_key=6bb3ab771fecb31508a3a12094d5f4b3&language=en-US")
    Call<MoviePool> listSearch(@Query("query") String s);

    @GET("search/tv?api_key=6bb3ab771fecb31508a3a12094d5f4b3&language=en-US")
    Call<TvShowPool> listSearchTv(@Query("query") String s);

    @GET("discover/movie?api_key=6bb3ab771fecb31508a3a12094d5f4b3")
    Call<MoviePool> listReleaseMovie(@QueryMap Map<String, String> option);
}
