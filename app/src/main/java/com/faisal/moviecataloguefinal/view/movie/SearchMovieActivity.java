package com.faisal.moviecataloguefinal.view.movie;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.adapter.MovieAdapter;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.presenter.PresenterSearchMovie;
import com.faisal.moviecataloguefinal.view.SearchMovieView;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieView {
    PresenterSearchMovie presenterSearchMovie;
    List<Movie> movies;
    RecyclerView listRv;
    MovieAdapter adapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie);

        movies = new ArrayList<>();
        adapter = new MovieAdapter(this, movies);
        listRv = findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(adapter);

        presenterSearchMovie = new PresenterSearchMovie(this,this);

        progressBar = findViewById(R.id.progress_list);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 5) {

                    presenterSearchMovie.getSearchMovie(newText.toLowerCase());
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    movies.clear();
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        searchItem.setActionView(searchView);
        return true;
    }

    @Override
    public void getMovieSearch(List<Movie> movieList) {
        movies.clear();
        movies.addAll(movieList);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
    }

}

