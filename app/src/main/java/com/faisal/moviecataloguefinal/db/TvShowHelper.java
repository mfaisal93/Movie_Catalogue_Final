package com.faisal.moviecataloguefinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.TABLE_NAME;

public class TvShowHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static TvShowDatabaseHelper databaseHelper;
    private static TvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvShowHelper(Context context) {
        databaseHelper = new TvShowDatabaseHelper(context);
    }

    public static TvShowHelper getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC");
    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null
                , ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public void update(String id, ContentValues values) {
        database.update(DATABASE_TABLE, values, ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, ID + " = ?", new String[]{id});
    }
}
