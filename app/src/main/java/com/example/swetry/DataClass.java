package com.example.swetry;

import android.os.Parcel;
import android.os.Parcelable;

public class DataClass implements Parcelable {
    private String title;
    private String rating;
    private String posterUrl;
    private String year;
    private String genre;
    private String plot;


    public DataClass(String title, String rating, String posterUrl, String year,String genre, String plot) {
        this.title = title;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.year = year;
        this.genre = genre;
        this.plot = plot;
    }

    protected DataClass(Parcel in) {
        title = in.readString();
        rating = in.readString();
        posterUrl = in.readString();
        year = in.readString();
        genre = in.readString();
        plot = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(rating);
        dest.writeString(posterUrl);
        dest.writeString(year);
        dest.writeString(genre);
        dest.writeString(plot);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataClass> CREATOR = new Creator<DataClass>() {
        @Override
        public DataClass createFromParcel(Parcel in) {
            return new DataClass(in);
        }

        @Override
        public DataClass[] newArray(int size) {
            return new DataClass[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
    public String getYear() { return year; }
    public String getGenre() {  return genre; }
    public String getPlot() {  return plot; }
}
