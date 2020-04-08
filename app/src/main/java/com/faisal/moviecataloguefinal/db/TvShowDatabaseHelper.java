package com.faisal.moviecataloguefinal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.TABLE_NAME;

public class TvShowDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbtvshow";

    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.TvShowColums.ID,
            DatabaseContract.TvShowColums.TITLE,
            DatabaseContract.TvShowColums.OVERVIEW,
            DatabaseContract.TvShowColums.DATE,
            DatabaseContract.TvShowColums.POSTER,
            DatabaseContract.TvShowColums.VOTE);

    public TvShowDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
