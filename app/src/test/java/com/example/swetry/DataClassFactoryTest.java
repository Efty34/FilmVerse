package com.example.swetry;

import static org.junit.Assert.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DataClassFactoryTest {

    @Test
    public void testCreateFromValidJson() throws Exception {
        // This will work
        JSONObject sampleMovie = new JSONObject();
        sampleMovie.put("Title", "The Shawshank Redemption");

        JSONArray ratingsArray = new JSONArray();
        JSONObject ratingObject1 = new JSONObject();
        ratingObject1.put("Source", "Internet Movie Database");
        ratingObject1.put("Value", "9.3/10");
        ratingsArray.put(ratingObject1);

        JSONObject ratingObject2 = new JSONObject();
        ratingObject2.put("Source", "Rotten Tomatoes");
        ratingObject2.put("Value", "91%");
        ratingsArray.put(ratingObject2);

        sampleMovie.put("Ratings", ratingsArray);
        sampleMovie.put("Poster", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg");
        sampleMovie.put("Year", "1994");
        sampleMovie.put("Genre", "Drama");
        sampleMovie.put("Plot", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");

        DataClass dataClass = DataClassFactory.createFromJson(sampleMovie);

        assertNotNull(dataClass);
        assertEquals("The Shawshank Redemption", dataClass.getTitle());
        assertEquals("9.3/10", dataClass.getRating());
        assertEquals("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg", dataClass.getPosterUrl());
        assertEquals("1994", dataClass.getYear());
        assertEquals("Drama", dataClass.getGenre());
        assertEquals("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", dataClass.getPlot());
    }

    @Test
    public void testCreateFromJsonWithMissingOptionalFields() throws Exception {
        // This will not work, as the JSON object is missing the Year, Genre, and Plot fields
        JSONObject sampleMovie = new JSONObject();
        sampleMovie.put("Title", "The Godfather");

        JSONArray ratingsArray = new JSONArray();
        JSONObject ratingObject = new JSONObject();
        ratingObject.put("Source", "Internet Movie Database");
        ratingObject.put("Value", "9.2/10");
        ratingsArray.put(ratingObject);

        sampleMovie.put("Ratings", ratingsArray);
        sampleMovie.put("Poster", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg");

        DataClass dataClass = DataClassFactory.createFromJson(sampleMovie);

        assertNotNull(dataClass);
        assertEquals("The Godfather", dataClass.getTitle());
        assertEquals("9.2/10", dataClass.getRating());
        assertEquals("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg", dataClass.getPosterUrl());
        assertNull(dataClass.getYear());
        assertNull(dataClass.getGenre());
        assertNull(dataClass.getPlot());
    }

    @Test
    public void testCreateFromInvalidJson() {
        try {
            JSONObject invalidMovie = new JSONObject();
            invalidMovie.put("Title", "Invalid Movie");
            // Ratings array is missing
            invalidMovie.put("Poster", "https://invalidurl.com/poster.jpg");
            invalidMovie.put("Year", "2021");
            invalidMovie.put("Genre", "Action, Adventure");
            invalidMovie.put("Plot", "This is an invalid plot.");

            DataClass dataClass = DataClassFactory.createFromJson(invalidMovie);

            assertNull("Expected an Exception to be thrown", dataClass);
        } catch (Exception e) {
            assertEquals("JSONObject[\"Ratings\"] not found.", e.getMessage());
        }
    }

}