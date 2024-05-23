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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GenreFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6;
    Map<String, List<DataClass>> genreMap = new HashMap<>();
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
        searchEditText.setTextColor(color);

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

        loadMoviesFromAssets();
        setAdapters();
    }

    private void loadMoviesFromAssets() {
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
                    if (!genreMap.containsKey(genre)) {
                        genreMap.put(genre, new ArrayList<>());
                    }
                    genreMap.get(genre).add(dataClass);
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapters() {
        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Biography", new ArrayList<>()), true);
        recyclerView1.setAdapter(adapter);

        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Crime", new ArrayList<>()), true);
        recyclerView2.setAdapter(adapter);

        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Adventure", new ArrayList<>()), true);
        recyclerView3.setAdapter(adapter);

        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Action", new ArrayList<>()), true);
        recyclerView4.setAdapter(adapter);

        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Romance", new ArrayList<>()), true);
        recyclerView5.setAdapter(adapter);

        adapter = new MyAdapter(getActivity(), genreMap.getOrDefault("Thriller", new ArrayList<>()), true);
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
        for (Map.Entry<String, List<DataClass>> entry : genreMap.entrySet()) {
            for (DataClass data : entry.getValue()) {
                if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    dataSearchList.add(data);
                }
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(getActivity(), "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
