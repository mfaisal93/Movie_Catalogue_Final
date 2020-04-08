package com.faisal.moviecataloguefinal.view.tvshow;

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
import com.faisal.moviecataloguefinal.adapter.TvShowAdapter;
import com.faisal.moviecataloguefinal.model.TvShow;
import com.faisal.moviecataloguefinal.presenter.PresenterListTv;
import com.faisal.moviecataloguefinal.view.ReminderActivity;
import com.faisal.moviecataloguefinal.view.TvListView;

import java.util.ArrayList;
import java.util.List;

public class TvShowFragment extends Fragment implements TvListView {
    List<TvShow> tvShows;
    RecyclerView listRv;
    TvShowAdapter adapter;
    ProgressBar progressBar;

    public TvShowFragment() {
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

        tvShows = new ArrayList<>();
        adapter = new TvShowAdapter(view.getContext(), tvShows);
        listRv = view.findViewById(R.id.rv_list);
        listRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listRv.setAdapter(adapter);

        PresenterListTv prepresenterListTv = new PresenterListTv(view.getContext(), this);
        prepresenterListTv.getListTv();
        progressBar = view.findViewById(R.id.progress_list);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedIntanceState) {
        super.onActivityCreated(savedIntanceState);
    }

    @Override
    public void getTvList(List<TvShow> tv) {
        tvShows.addAll(tv);
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
            Intent intent = new Intent(getContext(), SearchTvShowActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_change_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }else if (item.getItemId() == R.id.reminder_settings) {
            Intent intent = new Intent(getContext(), ReminderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
