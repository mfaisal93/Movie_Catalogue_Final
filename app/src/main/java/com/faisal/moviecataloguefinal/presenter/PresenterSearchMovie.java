package com.faisal.moviecataloguefinal.presenter;

import android.content.Context;

import com.faisal.moviecataloguefinal.api.ApiClient;
import com.faisal.moviecataloguefinal.api.ApiInterface;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.model.MoviePool;
import com.faisal.moviecataloguefinal.view.SearchMovieView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSearchMovie {
    private Context context;
    private SearchMovieView searchMovieView;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public PresenterSearchMovie(Context context, SearchMovieView searchMovieView) {
        this.context = context;
        this.searchMovieView = searchMovieView;
    }

    public void getSearchMovie(String movie) {
        Call<MoviePool> call = apiInterface.listSearch(movie);

        call.enqueue(new Callback<MoviePool>() {
            @Override
            public void onResponse(Call<MoviePool> call, Response<MoviePool> response) {
                if (response.body() != null) {
                    List<Movie> movies = response.body().getMovieList();
                    searchMovieView.getMovieSearch(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviePool> call, Throwable t) {

            }
        });
    }

}
