package com.example.swetry;


import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MovieRepository {
    private static MovieRepository instance;
    private List<DataClass> movies;
    private List<MovieDataObserver> observers = new ArrayList<>();

    private MovieRepository(Context context) {
        movies = new ArrayList<>();
        loadMoviesFromAssets(context);
    }

    public static synchronized MovieRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MovieRepository(context);
        }
        return instance;
    }

    private void loadMoviesFromAssets(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            loadMoviesFromJson(builder.toString());
        } catch (Exception e) {
            if (loadMoviesCallback != null) {
                loadMoviesCallback.onError("Error loading movies");
            }
        }
    }

    // New method to load movies from a JSON string
    public void loadMoviesFromJson(String json) {
        try {
            JSONObject root = new JSONObject(json);
            JSONArray moviesArray = root.getJSONArray("movies");
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject movie = moviesArray.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);
                movies.add(dataClass);
            }
            notifyObservers();
        } catch (Exception e) {
            if (loadMoviesCallback != null) {
                loadMoviesCallback.onError("Error loading movies");
            }
        }
    }

    public void addObserver(MovieDataObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MovieDataObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (MovieDataObserver observer : observers) {
            observer.onMovieDataChanged();
        }
    }

    public DataClass getRandomMovie() {
        Random random = new Random();
        return movies.get(random.nextInt(movies.size()));
    }

    public List<DataClass> getMovies() {
        return movies;
    }

    // Added method for testing purposes
    public void testNotifyObservers() {
        notifyObservers();
    }

    private LoadMoviesCallback loadMoviesCallback;

    public void setLoadMoviesCallback(LoadMoviesCallback callback) {
        this.loadMoviesCallback = callback;
    }

    public interface LoadMoviesCallback {
        void onError(String message);
    }
}

