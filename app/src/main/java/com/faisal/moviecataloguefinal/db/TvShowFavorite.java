package com.faisal.moviecataloguefinal.db;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowFavorite implements Parcelable {
    public static final Parcelable.Creator<TvShowFavorite> CREATOR = new Parcelable.Creator<TvShowFavorite>() {
        @Override
        public TvShowFavorite createFromParcel(Parcel source) {
            return new TvShowFavorite(source);
        }

        @Override
        public TvShowFavorite[] newArray(int size) {
            return new TvShowFavorite[size];
        }
    };
    public int id;
    public String title;
    public String overview;
    public String date;
    public String poster;
    public float vote;

    public TvShowFavorite(int id, String title, String overview, String date, String poster, float vote) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.date = date;
        this.poster = poster;
        this.vote = vote;
    }

    public TvShowFavorite() {
    }

    protected TvShowFavorite(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.poster = in.readString();
        this.vote = in.readFloat();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.date);
        dest.writeString(this.poster);
        dest.writeFloat(this.vote);
    }
}
