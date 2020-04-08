package com.faisal.consumerfavorite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faisal.consumerfavorite.R;
import com.faisal.consumerfavorite.model.MovieFavorite;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FMovieViewHolder> {
    private ArrayList<MovieFavorite> movieFavorite = new ArrayList<>();
    private Context context;

    public FavoriteMovieAdapter(Context context) {
    this.context = context;
    }

    public void setMovieFavorite(ArrayList<MovieFavorite> movieFavorite) {

        if (movieFavorite.size() > 0){
            this.movieFavorite.clear();
        }

        this.movieFavorite.addAll(movieFavorite);
        notifyDataSetChanged();
    }

    public ArrayList<MovieFavorite> getMovieFavorite() {
        return movieFavorite;
    }




    @NonNull
    @Override
    public FMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new FMovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FMovieViewHolder holder, int position) {
        final MovieFavorite movieFavoritedb = movieFavorite.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w92"+movieFavoritedb.getPoster()).into(holder.imageView);
        holder.txtJudul.setText(movieFavoritedb.getTitle());
        holder.txtRilis.setText(movieFavoritedb.getDate());

       }

    @Override
    public int getItemCount() {
        return movieFavorite.size();
    }

    class FMovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtJudul;
        private TextView txtRilis;
        private CardView toDetail;

        FMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_poster);
            txtJudul = itemView.findViewById(R.id.tv_judul);
            txtRilis = itemView.findViewById(R.id.tv_rilis);
            toDetail = itemView.findViewById(R.id.header);
        }
    }
}
