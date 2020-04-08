package com.faisal.moviecataloguefinal.presenter;

import android.content.Context;

import com.faisal.moviecataloguefinal.api.ApiClient;
import com.faisal.moviecataloguefinal.api.ApiInterface;
import com.faisal.moviecataloguefinal.model.TvShow;
import com.faisal.moviecataloguefinal.model.TvShowPool;
import com.faisal.moviecataloguefinal.view.SearchTvView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSearchTv {
    private Context context;
    private SearchTvView searchTvView;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public PresenterSearchTv(Context context, SearchTvView searchTvView) {
        this.context = context;
        this.searchTvView = searchTvView;
    }

    public void getSearchTv(String tv) {
        Call<TvShowPool> call = apiInterface.listSearchTv(tv);

        call.enqueue(new Callback<TvShowPool>() {
            @Override
            public void onResponse(Call<TvShowPool> call, Response<TvShowPool> response) {
                if (response.body() != null) {
                    List<TvShow> tvShows = response.body().getTvShowsList();
                    searchTvView.getTvSearch(tvShows);
                }
            }

            @Override
            public void onFailure(Call<TvShowPool> call, Throwable t) {

            }
        });
    }
}
