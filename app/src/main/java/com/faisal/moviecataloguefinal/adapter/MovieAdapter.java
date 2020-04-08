package com.faisal.moviecataloguefinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faisal.moviecataloguefinal.R;
import com.faisal.moviecataloguefinal.model.Movie;
import com.faisal.moviecataloguefinal.view.movie.DetailMovieActivity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        final Movie movie = movies.get(i);
        Glide.with(context).load("https://image.tmdb.org/t/p/w92" + movie.getPoster()).into(movieViewHolder.imageView);
        movieViewHolder.txtJudul.setText(movie.getTitle());
        movieViewHolder.txtRilis.setText(movie.getDate());
        movieViewHolder.toDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtJudul;
        private TextView txtRilis;
        private CardView toDetail;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_poster);
            txtJudul = itemView.findViewById(R.id.tv_judul);
            txtRilis = itemView.findViewById(R.id.tv_rilis);
            toDetail = itemView.findViewById(R.id.header);

        }
    }
}


