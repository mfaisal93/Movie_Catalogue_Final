package com.faisal.consumerfavorite.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.consumerfavorite.R;
import com.faisal.consumerfavorite.adapter.FavoriteMovieAdapter;
import com.faisal.consumerfavorite.db.DatabaseContract;
import com.faisal.consumerfavorite.model.MovieFavorite;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<MovieFavorite> favoriteList;
    RecyclerView listRv;
    FavoriteMovieAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_list);
        progressBar.setVisibility(View.INVISIBLE);

        favoriteList = new ArrayList<>();
        adapter = new FavoriteMovieAdapter(this);
        listRv = findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(adapter);

        loadDB();

    }


    private void loadDB(){
        Cursor dataCursor = this.getContentResolver().query(DatabaseContract.MovieColums.CONTENT_URI,null,null,null,null);
        while (dataCursor.moveToNext()){
            int id = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.ID));
            String title = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.TITLE));
            String ovv = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.OVERVIEW));
            String date = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.DATE));
            String poster = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.POSTER));
            float vote = dataCursor.getFloat(dataCursor.getColumnIndexOrThrow(DatabaseContract.MovieColums.VOTE));

            favoriteList.add(new MovieFavorite(id,title,ovv,date,poster,vote));

            adapter.setMovieFavorite(favoriteList);
        }
    }

}
