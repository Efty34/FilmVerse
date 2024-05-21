package com.example.swetry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenreFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6;
    List<DataClass> biographyList = new ArrayList<>();
    List<DataClass> crimeList = new ArrayList<>();
    List<DataClass> adventureList = new ArrayList<>();
    List<DataClass> actionList = new ArrayList<>();
    List<DataClass> romanceList = new ArrayList<>();
    List<DataClass> thrillerList = new ArrayList<>();
    MyAdapter adapter;
    SearchView searchView;
    EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        recyclerView4 = view.findViewById(R.id.recyclerView4);
        recyclerView5 = view.findViewById(R.id.recyclerView5);
        recyclerView6 = view.findViewById(R.id.recyclerView6);

        searchView = view.findViewById(R.id.search);

        // Color the search hint
        int color = ContextCompat.getColor(requireContext(), R.color.white);
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(color);

        initializeRecyclerView();
        setupSearchView();

        return view;
    }

    private void initializeRecyclerView() {
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView5.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView6.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadMoviesFromAssets1();
        loadMoviesFromAssets2();
        loadMoviesFromAssets3();
        loadMoviesFromAssets4();
        loadMoviesFromAssets5();
        loadMoviesFromAssets6();
    }

    // Biography
    private void loadMoviesFromAssets1() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Biography")) {
                        biographyList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), biographyList, true); // Enable resizing
        recyclerView1.setAdapter(adapter);
    }

    // Crime
    private void loadMoviesFromAssets2() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Crime")) {
                        crimeList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), crimeList, true); // Enable resizing
        recyclerView2.setAdapter(adapter);
    }

    // Adventure
    private void loadMoviesFromAssets3() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Adventure")) {
                        adventureList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), adventureList, true); // Enable resizing
        recyclerView3.setAdapter(adapter);
    }

    // Action
    private void loadMoviesFromAssets4() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Action")) {
                        actionList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), actionList, true); // Enable resizing
        recyclerView4.setAdapter(adapter);
    }

    // Romance
    private void loadMoviesFromAssets5() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Romance")) {
                        romanceList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), romanceList, true); // Enable resizing
        recyclerView5.setAdapter(adapter);
    }

    // Thriller
    private void loadMoviesFromAssets6() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            JSONObject root = new JSONObject(builder.toString());
            JSONArray movies = root.getJSONArray("movies");

            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);

                String genres = movie.getString("Genre");
                String[] genreList = genres.split(", ");
                for (String genre : genreList) {
                    if (genre.equals("Thriller")) {
                        thrillerList.add(dataClass);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }

        adapter = new MyAdapter(getActivity(), thrillerList, true); // Enable resizing
        recyclerView6.setAdapter(adapter);
    }

    private void setupSearchView() {
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        // Search in biographyList
        for (DataClass data : biographyList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }

        // Search in crimeList
        for (DataClass data : crimeList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }

        // Repeat for other genre lists
        for (DataClass data : adventureList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }

        for (DataClass data : actionList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }

        for (DataClass data : romanceList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }

        for (DataClass data : thrillerList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
