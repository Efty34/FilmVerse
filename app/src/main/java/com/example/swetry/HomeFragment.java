package com.example.swetry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    SearchView searchView;
    EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.search);

        //coloring search hint
        int color = ContextCompat.getColor(requireContext(), R.color.white);
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(color);
        searchEditText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));

        initializeRecyclerView();
        setupSearchView();

        return view;
    }

    private void initializeRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        loadMoviesFromAssets();  // Load movie data

        adapter = new MyAdapter(getActivity(), dataList, false); // Enable resizing

        recyclerView.setAdapter(adapter);
    }

    private void loadMoviesFromAssets() {
        try {
            InputStream inputStream = getContext().getAssets().open("movies.json");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            dataList = parseMoviesJson(builder.toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading movies", Toast.LENGTH_SHORT).show();
        }
    }

    public List<DataClass> parseMoviesJson(String json) {
        List<DataClass> moviesList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONArray movies = root.getJSONArray("movies");
            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                DataClass dataClass = DataClassFactory.createFromJson(movie);
                int year = Integer.parseInt(dataClass.getYear());

                if (year >= 2018) {
                    moviesList.add(dataClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
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

    public void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
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
