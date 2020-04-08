package com.faisal.moviecataloguefinal.view.tvshow;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.db.TvShowFavorite;
import com.faisal.moviecataloguefinal.db.TvShowHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.DATE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.OVERVIEW;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.POSTER;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.TITLE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.TvShowColums.VOTE;

public class DetailFavoriteTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TVDB = "extra_tvdb";
    ImageView imgPoster, imgLayar;
    TextView txtJudul, txtRilis, txtOverView, txtUserScore;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TvShowHelper tvShowHelper;
    FloatingActionButton floatingAB;
    TvShowFavorite tvShowFavorite = new TvShowFavorite();
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TvShowFavorite data = getIntent().getParcelableExtra(EXTRA_TVDB);

        imgPoster = findViewById(R.id.img_poster);
        txtJudul = findViewById(R.id.tv_judul);
        txtRilis = findViewById(R.id.tv_rilis);
        txtOverView = findViewById(R.id.tv_overview);
        txtUserScore = findViewById(R.id.tv_user_score);
        imgLayar = findViewById(R.id.expandedImage);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + data.getPoster()).into(imgLayar);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + data.getPoster()).into(imgPoster);
        txtJudul.setText(data.getTitle());
        txtRilis.setText(data.getDate());
        txtOverView.setText(data.getOverview());
        txtUserScore.setText(String.valueOf(data.getVote()));

        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(data.getTitle());

        i = data.id;

        tvShowHelper = TvShowHelper.getINSTANCE(getApplicationContext());
        tvShowHelper.open();

        floatingAB = findViewById(R.id.floating_ab);

        if (isFavo()) {
            floatingAB.setImageResource(R.drawable.ic_favorite);
        } else {
            floatingAB.setImageResource(R.drawable.ic_favorite_border);
        }

        floatingAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFavo()) {
                    tvShowHelper.deleteById(String.valueOf(data.getId()));
                    Toast.makeText(DetailFavoriteTvShowActivity.this, R.string.delete, Toast.LENGTH_SHORT).show();
                    floatingAB.setImageResource(R.drawable.ic_favorite_border);
                } else {
                    tvShowFavorite.setId(data.getId());
                    tvShowFavorite.setTitle(data.getTitle());
                    tvShowFavorite.setOverview(data.getOverview());
                    tvShowFavorite.setDate(data.getDate());
                    tvShowFavorite.setPoster(data.getPoster());
                    tvShowFavorite.setVote(data.getVote());

                    ContentValues values = new ContentValues();
                    values.put(ID, data.getId());
                    values.put(TITLE, data.getTitle());
                    values.put(OVERVIEW, data.getOverview());
                    values.put(DATE, data.getDate());
                    values.put(POSTER, data.getPoster());
                    values.put(VOTE, data.getVote());
                    tvShowHelper.insert(values);
                    Toast.makeText(DetailFavoriteTvShowActivity.this, R.string.result, Toast.LENGTH_SHORT).show();
                    floatingAB.setImageResource(R.drawable.ic_favorite);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isFavo() {
        boolean favo = false;
        Cursor id = tvShowHelper.queryById(String.valueOf(i));
        id.moveToFirst();
        if (id.getCount() > 0) {
            i = id.getInt(id.getColumnIndexOrThrow(ID));
            favo = true;
        }
        return favo;
    }


}

