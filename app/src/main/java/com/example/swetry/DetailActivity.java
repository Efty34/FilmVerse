package com.example.swetry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView detailRating, detailTitle, detailYear, detailPlot, genre1, genre2, genre3;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Log the received movie data
        DataClass movie = getIntent().getParcelableExtra("MovieData");
        if (movie != null) {
            Log.d("DetailActivity", "Received movie: " + movie.getTitle() + ", Genres: " + movie.getGenre());
        } else {
            Log.d("DetailActivity", "No movie data received");
        }

        // Setting up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailRating = findViewById(R.id.detailRating);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailYear = findViewById(R.id.detailYear);
//        detailGenre = findViewById(R.id.detailGenre);
        genre1 = findViewById(R.id.genre1);
        genre2 = findViewById(R.id.genre2);
        genre3 = findViewById(R.id.genre3);

        detailPlot = findViewById(R.id.detailPlot);

        movie = getIntent().getParcelableExtra("MovieData");
        if (movie != null) {
            detailTitle.setText(movie.getTitle());
            detailRating.setText(movie.getRating());
            detailYear.setText(movie.getYear());
            detailPlot.setText(movie.getPlot());

//            String[] genres = movie.getGenre().split(", ");
//            int genreCount = genres.length;


            String genreString = movie.getGenre();
            if (genreString != null && !genreString.isEmpty()) {
                String[] genres = genreString.split(", ");
                switch (genres.length) {
                    case 1:
                        genre1.setText(genres[0]);
                        genre2.setVisibility(View.GONE);
                        genre3.setVisibility(View.GONE);
                        break;
                    case 2:
                        genre1.setText(genres[0]);
                        genre2.setText(genres[1]);
                        genre3.setVisibility(View.GONE);
                        break;
                    case 3:
                        genre1.setText(genres[0]);
                        genre2.setText(genres[1]);
                        genre3.setText(genres[2]);
                        break;
                    default:
                        genre1.setVisibility(View.GONE);
                        genre2.setVisibility(View.GONE);
                        genre3.setVisibility(View.GONE);
                        break;
                }
            } else {
                genre1.setVisibility(View.GONE);
                genre2.setVisibility(View.GONE);
                genre3.setVisibility(View.GONE);
            }

            Picasso.get().load(movie.getPosterUrl()).into(detailImage);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}