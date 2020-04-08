package com.faisal.moviecataloguefinal.view.movie;

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
import com.faisal.moviecataloguefinal.db.MovieFavorite;
import com.faisal.moviecataloguefinal.db.MovieHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.DATE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.ID;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.OVERVIEW;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.POSTER;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.TITLE;
import static com.faisal.moviecataloguefinal.db.DatabaseContract.MovieColums.VOTE;

public class DetailFavoriteMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIEDB = "extra_moviedb";
    ImageView imgPoster, imgLayar;
    TextView txtJudul, txtRilis, txtOverView, txtUserScore;
    CollapsingToolbarLayout collapsingToolbarLayout;
    MovieHelper movieHelper;
    FloatingActionButton floatingAB;
    MovieFavorite movieFavorite = new MovieFavorite();
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final MovieFavorite data = getIntent().getParcelableExtra(EXTRA_MOVIEDB);

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

        movieHelper = MovieHelper.getINSTANCE(getApplicationContext());
        movieHelper.open();

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
                    movieHelper.deleteById(String.valueOf(data.getId()));
                    Toast.makeText(DetailFavoriteMovieActivity.this, R.string.delete, Toast.LENGTH_SHORT).show();
                    floatingAB.setImageResource(R.drawable.ic_favorite_border);
                } else {
                    movieFavorite.setId(data.getId());
                    movieFavorite.setTitle(data.getTitle());
                    movieFavorite.setOverview(data.getOverview());
                    movieFavorite.setDate(data.getDate());
                    movieFavorite.setPoster(data.getPoster());
                    movieFavorite.setVote(data.getVote());

                    ContentValues values = new ContentValues();
                    values.put(ID, data.getId());
                    values.put(TITLE, data.getTitle());
                    values.put(OVERVIEW, data.getOverview());
                    values.put(DATE, data.getDate());
                    values.put(POSTER, data.getPoster());
                    values.put(VOTE, data.getVote());
                    movieHelper.insert(values);
                    Toast.makeText(DetailFavoriteMovieActivity.this, R.string.result, Toast.LENGTH_SHORT).show();
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
        Cursor id = movieHelper.queryById(String.valueOf(i));
        id.moveToFirst();
        if (id.getCount() > 0) {
            i = id.getInt(id.getColumnIndexOrThrow(ID));
            favo = true;
        }
        return favo;
    }


}
