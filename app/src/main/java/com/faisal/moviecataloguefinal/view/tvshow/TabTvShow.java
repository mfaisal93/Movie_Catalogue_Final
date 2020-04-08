package com.faisal.moviecataloguefinal.view.tvshow;


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
import com.faisal.moviecataloguefinal.adapter.FavoriteTvShowAdapter;
import com.faisal.moviecataloguefinal.db.TvShowFavorite;
import com.faisal.moviecataloguefinal.db.TvShowHelper;

import java.util.ArrayList;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.DATE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.OVERVIEW;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.POSTER;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.TITLE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.VOTE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTvShow extends Fragment {
    ArrayList<TvShowFavorite> favoriteList;
    RecyclerView listRv;
    FavoriteTvShowAdapter adapter;
    TvShowHelper tvShowHelper;
    ProgressBar progressBar;


    public TabTvShow() {
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
        adapter = new FavoriteTvShowAdapter(view.getContext());
        listRv = view.findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listRv.setAdapter(adapter);

        tvShowHelper = TvShowHelper.getINSTANCE(view.getContext());
        tvShowHelper.open();

        loadDB();

    }

    private void loadDB() {
        Cursor dataCursor = tvShowHelper.queryAll();
        while (dataCursor.moveToNext()) {
            int id = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(ID));
            String title = dataCursor.getString(dataCursor.getColumnIndexOrThrow(TITLE));
            String ovv = dataCursor.getString(dataCursor.getColumnIndexOrThrow(OVERVIEW));
            String date = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DATE));
            String poster = dataCursor.getString(dataCursor.getColumnIndexOrThrow(POSTER));
            float vote = dataCursor.getFloat(dataCursor.getColumnIndexOrThrow(VOTE));

            favoriteList.add(new TvShowFavorite(id, title, ovv, date, poster, vote));

        }

        adapter.setTvShowFavorite(favoriteList);

    }
}
