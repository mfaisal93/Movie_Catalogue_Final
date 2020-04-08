package com.faisal.moviecataloguefinal.presenter;

import android.content.Context;

import com.faisal.moviecataloguefinal.api.ApiClient;
import com.faisal.moviecataloguefinal.api.ApiInterface;
import com.faisal.moviecataloguefinal.model.TvShow;
import com.faisal.moviecataloguefinal.model.TvShowPool;
import com.faisal.moviecataloguefinal.view.TvListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterListTv {
    private Context context;
    private TvListView tvListView;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public PresenterListTv(Context context, TvListView tvListView) {
        this.context = context;
        this.tvListView = tvListView;
    }

    public void getListTv() {
        Call<TvShowPool> call = apiInterface.listTv("tv");

        call.enqueue(new Callback<TvShowPool>() {
            @Override
            public void onResponse(Call<TvShowPool> call, Response<TvShowPool> response) {
                if (response.body() != null) {
                    List<TvShow> tvShows = response.body().getTvShowsList();
                    tvListView.getTvList(tvShows);
                }
            }

            @Override
            public void onFailure(Call<TvShowPool> call, Throwable t) {

            }
        });
    }

}
