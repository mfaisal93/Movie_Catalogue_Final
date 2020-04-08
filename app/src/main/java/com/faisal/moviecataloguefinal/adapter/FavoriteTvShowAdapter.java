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
import com.faisal.moviecataloguefinal.db.TvShowFavorite;
import com.faisal.moviecataloguefinal.view.tvshow.DetailFavoriteTvShowActivity;

import java.util.ArrayList;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.FTvShowViewHolder> {
    private ArrayList<TvShowFavorite> tvShowFavorites = new ArrayList<>();
    private Context context;

    public FavoriteTvShowAdapter(Context context) {
        this.context = context;
    }

    public void setTvShowFavorite(ArrayList<TvShowFavorite> tvShowFavorite) {

        if (tvShowFavorites.size() > 0) {
            this.tvShowFavorites.clear();
        }

        this.tvShowFavorites.addAll(tvShowFavorite);
        notifyDataSetChanged();
    }

    public ArrayList<TvShowFavorite> getMovieFavorite() {
        return tvShowFavorites;
    }


    @NonNull
    @Override
    public FTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new FTvShowViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FTvShowViewHolder holder, int position) {
        final TvShowFavorite tvFavoritedb = tvShowFavorites.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w92" + tvFavoritedb.getPoster()).into(holder.imageView);
        holder.txtJudul.setText(tvFavoritedb.getTitle());
        holder.txtRilis.setText(tvFavoritedb.getDate());
        holder.toDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailFavoriteTvShowActivity.class);
                i.putExtra(DetailFavoriteTvShowActivity.EXTRA_TVDB, tvFavoritedb);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowFavorites.size();
    }

    class FTvShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtJudul;
        private TextView txtRilis;
        private CardView toDetail;

        FTvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_poster);
            txtJudul = itemView.findViewById(R.id.tv_judul);
            txtRilis = itemView.findViewById(R.id.tv_rilis);
            toDetail = itemView.findViewById(R.id.header);
        }
    }
}

