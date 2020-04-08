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
import com.faisal.moviecataloguefinal.model.TvShow;
import com.faisal.moviecataloguefinal.view.tvshow.DetailTvShowActivity;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private Context context;
    private List<TvShow> tvShows;

    public TvShowAdapter(Context context, List<TvShow> tvShwos) {
        this.context = context;
        this.tvShows = tvShwos;
    }


    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new TvShowAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder tvShowViewHolder, int i) {
        final TvShow tv = tvShows.get(i);
        Glide.with(context).load("https://image.tmdb.org/t/p/w92" + tv.getPoster()).into(tvShowViewHolder.imageView);
        tvShowViewHolder.txtJudul.setText(tv.getName());
        tvShowViewHolder.txtRilis.setText(tv.getDate());
        tvShowViewHolder.toDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV, tv);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtJudul;
        private TextView txtRilis;
        private CardView toDetail;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_poster);
            txtJudul = itemView.findViewById(R.id.tv_judul);
            txtRilis = itemView.findViewById(R.id.tv_rilis);
            toDetail = itemView.findViewById(R.id.header);
        }
    }
}

