package com.faisal.moviecataloguefinal.db;

import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {
    public static final String AUTHORITY = "com.faisal.moviecataloguefinal";
    private static final String SCHEME = "content";

    public static final class MovieColums implements BaseColumns {
        public static final String TABLE_NAME = "movie";

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String DATE = "date";
        public static final String POSTER = "poster";
        public static final String VOTE = "vote";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static final class TvShowColums implements BaseColumns {
        public static final String TABLE_NAME = "tvshow";

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String DATE = "date";
        public static final String POSTER = "poster";
        public static final String VOTE = "vote";
    }

}
