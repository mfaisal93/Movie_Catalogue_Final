package com.faisal.moviecataloguefinal.presenter;

import android.content.Context;

import com.faisal.moviecataloguefinal.api.ApiClient;
import com.faisal.moviecataloguefinal.api.ApiInterface;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.model.MoviePool;
import com.faisal.moviecataloguefinal.view.ReleaseMovieView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterReleaseMovie {
    private Context context;
    private ReleaseMovieView releaseMovieView;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public PresenterReleaseMovie(Context context, ReleaseMovieView releaseMovieView) {
        this.context = context;
        this.releaseMovieView = releaseMovieView;
    }

    public void getReleaseMovie(String movie) {
        Map<String, String> data = new HashMap<>();
        data.put("primary_release_date.gte", movie);
        data.put("primary_release_date.lte", movie);
        Call<MoviePool> call = apiInterface.listReleaseMovie(data);

        call.enqueue(new Callback<MoviePool>() {
            @Override
            public void onResponse(Call<MoviePool> call, Response<MoviePool> response) {
                if (response.body() != null) {
                    List<Movie> movies = response.body().getMovieList();
                    releaseMovieView.getReleaseMovie(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviePool> call, Throwable t) {

            }
        });
    }
}
