package com.faisal.moviecataloguefinal.presenter;

import android.content.Context;
import android.util.Log;

import com.faisal.moviecataloguefinal.api.ApiClient;
import com.faisal.moviecataloguefinal.api.ApiInterface;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.model.MoviePool;
import com.faisal.moviecataloguefinal.view.MovieListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterListMovie {
    private Context context;
    private MovieListView movieListView;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public PresenterListMovie(Context context, MovieListView movieListView) {
        this.context = context;
        this.movieListView = movieListView;
    }

    public void getListMovie(){
        Call<MoviePool> call = apiInterface.listMovies("movie");


        call.enqueue(new Callback<MoviePool>() {

            @Override
            public void onResponse(Call<MoviePool> call, Response<MoviePool> response) {
                if (response.body() != null) {
                    List<Movie> movies = response.body().getMovieList();
                    movieListView.getMovieList(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviePool> call, Throwable t) {
                Log.i("ERROR", t.toString());
            }
        });
    }

}
