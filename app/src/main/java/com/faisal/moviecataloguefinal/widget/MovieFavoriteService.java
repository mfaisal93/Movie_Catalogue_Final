package com.faisal.moviecataloguefinal.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class MovieFavoriteService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieFavoriteRemoteViewsFactory(this.getApplicationContext());
    }
}