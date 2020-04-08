package com.faisal.moviecataloguefinal.view.movie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.adapter.MovieAdapter;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.presenter.PresenterListMovie;
import com.faisal.moviecataloguefinal.view.MovieListView;
import com.faisal.moviecataloguefinal.view.ReminderActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment implements MovieListView {
    List<Movie> movies;
    RecyclerView listRv;
    MovieAdapter adapter;
    ProgressBar progressBar;

    public MovieFragment() {
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


        movies = new ArrayList<>();
        adapter = new MovieAdapter(view.getContext(), movies);
        listRv = view.findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listRv.setAdapter(adapter);

        PresenterListMovie presenterListMovie = new PresenterListMovie(view.getContext(), this);
        presenterListMovie.getListMovie();
        progressBar = view.findViewById(R.id.progress_list);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void getMovieList(List<Movie> movie) {
        movies.addAll(movie);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_button) {
            Intent intent = new Intent(getContext(), SearchMovieActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_change_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        } else if (item.getItemId() == R.id.reminder_settings) {
            Intent intent = new Intent(getContext(), ReminderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

