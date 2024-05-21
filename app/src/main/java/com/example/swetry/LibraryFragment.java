package com.example.swetry;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class LibraryFragment extends Fragment implements MovieDataObserver {

    private Button generateButton;
    private TextView randomMovieTitle;
    private TextView randomMovieGenre;
    private TextView randomMovieYear;
    private TextView randomMovieRating;
    private ImageView randomMoviePoster;
    private MovieRepository movieRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        generateButton = view.findViewById(R.id.generateButton);
        randomMovieTitle = view.findViewById(R.id.randomMovieTitle);
        randomMovieGenre = view.findViewById(R.id.randomMovieGenre);
        randomMovieYear = view.findViewById(R.id.randomMovieYear);
        randomMovieRating = view.findViewById(R.id.randomMovieRating);
        randomMoviePoster = view.findViewById(R.id.randomMoviePoster);

        movieRepository = MovieRepository.getInstance(requireContext());
        movieRepository.addObserver(this);

        generateButton.setOnClickListener(v -> {
            DataClass randomMovie = movieRepository.getRandomMovie();
            if (randomMovie != null) {
                updateUI(randomMovie);
            } else {
                Toast.makeText(requireContext(), "No movies available", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onMovieDataChanged() {
        // This method will be called when the movie data is loaded or updated
        Toast.makeText(requireContext(), "Movie data has been loaded/updated", Toast.LENGTH_SHORT).show();
        DataClass randomMovie = movieRepository.getRandomMovie();
        if (randomMovie != null) {
            updateUI(randomMovie);
        }
    }

    private void updateUI(DataClass randomMovie) {
        randomMovieTitle.setText(randomMovie.getTitle());
        randomMovieGenre.setText("Genre: " + randomMovie.getGenre());
        randomMovieYear.setText("Year: " + randomMovie.getYear());
        randomMovieRating.setText("Rating: " + randomMovie.getRating());
        Picasso.get().load(randomMovie.getPosterUrl()).into(randomMoviePoster);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieRepository.removeObserver(this);
    }
}
