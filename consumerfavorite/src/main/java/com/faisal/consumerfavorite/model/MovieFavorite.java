package com.faisal.consumerfavorite.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieFavorite implements Parcelable {

    public int id;
    public String title;
    public String overview;
    public String date;
    public String poster;
    public float vote;

    public MovieFavorite(int id, String title, String overview, String date, String poster, float vote) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.date = date;
        this.poster = poster;
        this.vote = vote;
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

    public MovieFavorite() {
    }

    protected MovieFavorite(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.poster = in.readString();
        this.vote = in.readFloat();
    }

    public static final Creator<MovieFavorite> CREATOR = new Creator<MovieFavorite>() {
        @Override
        public MovieFavorite createFromParcel(Parcel source) {
            return new MovieFavorite(source);
        }

        @Override
        public MovieFavorite[] newArray(int size) {
            return new MovieFavorite[size];
        }
    };
}