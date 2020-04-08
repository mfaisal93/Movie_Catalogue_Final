package com.faisal.moviecataloguefinal.view;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.view.movie.TabMovie;
import com.faisal.moviecataloguefinal.view.tvshow.TabTvShow;

public class TabAdapter extends FragmentPagerAdapter {
    int totaltab;
    private Context context;

    public TabAdapter(FragmentManager fm, Context context, int totaltab) {
        super(fm);
        this.context = context;
        this.totaltab = totaltab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabMovie tabMovie = new TabMovie();
                return tabMovie;
            case 1:
                TabTvShow tabTvShow = new TabTvShow();
                return tabTvShow;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totaltab;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.movies);
            case 1:
                return context.getString(R.string.tv_show);
            default:
                return null;

        }
    }
}
