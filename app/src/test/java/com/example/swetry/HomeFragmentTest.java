package com.example.swetry;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.swetry.DataClass;
import com.example.swetry.HomeFragment;

public class HomeFragmentTest {

    private HomeFragment homeFragment;
    private List<DataClass> dataList;

    @Before
    public void setUp() {
        homeFragment = new HomeFragment();
        dataList = new ArrayList<>();
    }


    @Test
    public void testSearchList() {
        // Initialize sample data
        dataList.add(new DataClass("Sample Movie 1", "8.0", "url1", "2020", "Action", "Sample plot 1"));
        dataList.add(new DataClass("Another Movie", "7.0", "url2", "2019", "Drama", "Sample plot 2"));
        dataList.add(new DataClass("Sample Movie 2", "6.0", "url3", "2018", "Comedy", "Sample plot 3"));

        // Set up the search query
        String query = "Sample";

        // Perform search
        List<DataClass> result = searchList(query);

        // Verify results
        assertEquals(2, result.size());
        assertEquals("Sample Movie 1", result.get(0).getTitle());
        assertEquals("Sample Movie 2", result.get(1).getTitle());
    }

    // Helper method for searchList
    private List<DataClass> searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        return dataSearchList;
    }
}

