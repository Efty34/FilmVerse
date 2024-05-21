package com.example.swetry;

import org.json.JSONObject;
public class DataClassFactory {
    public static DataClass createFromJson(JSONObject movie) throws Exception {
        String title = movie.getString("Title");
        String rating = movie.getJSONArray("Ratings").getJSONObject(0).getString("Value");
        String posterUrl = movie.getString("Poster");
        String yearRelease = movie.getString("Year");
        return new DataClass(title, rating, posterUrl, yearRelease, movie.getString("Genre"), movie.getString("Plot"));
    }
}