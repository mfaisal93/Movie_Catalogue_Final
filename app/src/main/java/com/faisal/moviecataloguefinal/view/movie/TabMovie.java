package com.faisal.moviecataloguefinal.view.movie;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.adapter.FavoriteMovieAdapter;
import com.faisal.moviecataloguefinal.db.DatabaseContract;
import com.faisal.moviecataloguefinal.db.MovieFavorite;
import com.faisal.moviecataloguefinal.db.MovieHelper;

import java.util.ArrayList;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.DATE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.POSTER;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.TITLE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.VOTE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovie extends Fragment {
    ArrayList<MovieFavorite> favoriteList;
    RecyclerView listRv;
    FavoriteMovieAdapter adapter;
    MovieHelper movieHelper;
    ProgressBar progressBar;

    public TabMovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_list);
        progressBar.setVisibility(View.INVISIBLE);

        favoriteList = new ArrayList<>();
        adapter = new FavoriteMovieAdapter(view.getContext());
        listRv = view.findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listRv.setAdapter(adapter);

        movieHelper = MovieHelper.getINSTANCE(view.getContext());
        movieHelper.open();
        loadDB();

    }

    private void loadDB() {
        Cursor dataCursor = getContext().getContentResolver().query(DatabaseContract.MovieColums.CONTENT_URI, null, null, null, null);
        while (dataCursor.moveToNext()) {
            int id = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(ID));
            String title = dataCursor.getString(dataCursor.getColumnIndexOrThrow(TITLE));
            String ovv = dataCursor.getString(dataCursor.getColumnIndexOrThrow(OVERVIEW));
            String date = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DATE));
            String poster = dataCursor.getString(dataCursor.getColumnIndexOrThrow(POSTER));
            float vote = dataCursor.getFloat(dataCursor.getColumnIndexOrThrow(VOTE));

            favoriteList.add(new MovieFavorite(id, title, ovv, date, poster, vote));

            adapter.setMovieFavorite(favoriteList);
        }
    }


}
