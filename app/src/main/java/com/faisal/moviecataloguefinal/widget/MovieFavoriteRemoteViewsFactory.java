package com.faisal.moviecataloguefinal.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.db.MovieFavorite;
import com.faisal.moviecataloguefinal.db.MovieHelper;

import java.util.ArrayList;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.DATE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.POSTER;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.TITLE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.VOTE;

public class MovieFavoriteRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    ArrayList<MovieFavorite> favoriteList = new ArrayList<>();
    MovieHelper movieHelper;
    Context context;

    public MovieFavoriteRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        movieHelper = MovieHelper.getINSTANCE(context);
        movieHelper.open();
    }

    @Override
    public void onDataSetChanged() {

        Cursor dataCursor = movieHelper.queryAll();
        favoriteList.clear();
        while (dataCursor.moveToNext()) {
            int id = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(ID));
            String title = dataCursor.getString(dataCursor.getColumnIndexOrThrow(TITLE));
            String ovv = dataCursor.getString(dataCursor.getColumnIndexOrThrow(OVERVIEW));
            String date = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DATE));
            String poster = dataCursor.getString(dataCursor.getColumnIndexOrThrow(POSTER));
            float vote = dataCursor.getFloat(dataCursor.getColumnIndexOrThrow(VOTE));
            favoriteList.add(new MovieFavorite(id, title, ovv, date, poster, vote));

        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return favoriteList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        MovieFavorite movieFavorite = favoriteList.get(i);
        try {
            Bitmap bit = Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/w92" + movieFavorite.getPoster()).submit(512, 512).get();
            rv.setImageViewBitmap(R.id.imageView, bit);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bundle extras = new Bundle();
        extras.putInt(MovieFavoriteWidget.EXTRA_ITEM, i);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
