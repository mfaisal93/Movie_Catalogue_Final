package com.faisal.moviecataloguefinal.view.tvshow;

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
import com.faisal.moviecataloguefinal.adapter.TvShowAdapter;
import com.faisal.moviecataloguefinal.model.TvShow;
import com.faisal.moviecataloguefinal.presenter.PresenterSearchTv;
import com.faisal.moviecataloguefinal.view.SearchTvView;

import java.util.ArrayList;
import java.util.List;

public class SearchTvShowActivity extends AppCompatActivity implements SearchTvView {
    PresenterSearchTv presenterSearchTv;
    List<TvShow> tvShow;
    RecyclerView listRv;
    TvShowAdapter adapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie);

        tvShow = new ArrayList<>();
        adapter = new TvShowAdapter(this, tvShow);
        listRv = findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(adapter);

        presenterSearchTv = new PresenterSearchTv(this, this);

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

                    presenterSearchTv.getSearchTv(newText.toLowerCase());
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    tvShow.clear();
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        searchItem.setActionView(searchView);
        return true;
    }

    @Override
    public void getTvSearch(List<TvShow> tvShowList) {
        tvShow.clear();
        tvShow.addAll(tvShowList);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
    }

}
