package com.example.swetry;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataClassTest {

    @Test
    public void testDataClassCreation() {
        String title = "The Shawshank Redemption";
        String rating = "9.3";
        String posterUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg";
        String year = "1994";
        String genre = "Drama";
        String plot = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.";

        DataClass data = new DataClass(title, rating, posterUrl, year, genre, plot);

        assertEquals(title, data.getTitle());
        assertEquals(rating, data.getRating());
        assertEquals(posterUrl, data.getPosterUrl());
        assertEquals(year, data.getYear());
        assertEquals(genre, data.getGenre());
        assertEquals(plot, data.getPlot());
    }

    @Test
    public void testDataClassParceling() {
        String title = "The Shawshank Redemption";
        String rating = "9.3";
        String posterUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg";
        String year = "1994";
        String genre = "Drama";
        String plot = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.";

        DataClass originalData = new DataClass(title, rating, posterUrl, year, genre, plot);

        DataClass newData = new DataClass(originalData.getTitle(), originalData.getRating(),
                originalData.getPosterUrl(), originalData.getYear(),
                originalData.getGenre(), originalData.getPlot());

        assertEquals(originalData.getTitle(), newData.getTitle());
        assertEquals(originalData.getRating(), newData.getRating());
        assertEquals(originalData.getPosterUrl(), newData.getPosterUrl());
        assertEquals(originalData.getYear(), newData.getYear());
        assertEquals(originalData.getGenre(), newData.getGenre());
        assertEquals(originalData.getPlot(), newData.getPlot());
    }
}