package com.example.swetry;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class LibraryFragment extends Fragment implements MovieDataObserver {

    private Button generateButton, submitGuessButton, nextMovieButton;
    private TextView randomMovieTitle, randomMovieGenre, randomMovieYear, randomMovieRating;
    private TextView gameResult, gameRating, gameYear, gameGenre, gameDescription, gameMovieName;
    private ImageView randomMoviePoster;
    private EditText guessInput;
    private MovieRepository movieRepository;
    private DataClass currentRandomMovie;
    private DataClass currentGameMovie;

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

        submitGuessButton = view.findViewById(R.id.submitGuessButton);
        nextMovieButton = view.findViewById(R.id.nextMovieButton);
        guessInput = view.findViewById(R.id.guessInput);
        gameResult = view.findViewById(R.id.gameResult);
        gameRating = view.findViewById(R.id.gamerating);
        gameYear = view.findViewById(R.id.gameyear);
        gameGenre = view.findViewById(R.id.gamegenre);
        gameDescription = view.findViewById(R.id.gameDescription);
        gameMovieName = view.findViewById(R.id.gamemoviename);


        movieRepository = MovieRepository.getInstance(requireContext());
        movieRepository.addObserver(this);

        generateButton.setOnClickListener(v -> {
            currentRandomMovie = movieRepository.getRandomMovie();
            if (currentRandomMovie != null) {
                updateRandomMovieUI(currentRandomMovie);
            } else {
                Toast.makeText(requireContext(), "No movies available", Toast.LENGTH_SHORT).show();
            }
        });
        nextMovieButton.setOnClickListener(v -> {
            currentGameMovie = movieRepository.getRandomMovie();
            if (currentGameMovie != null) {
                setGameDetails(currentGameMovie);
            } else {
                Toast.makeText(requireContext(), "No movies available", Toast.LENGTH_SHORT).show();
            }
        });

        submitGuessButton.setOnClickListener(v -> {
            if (currentGameMovie != null) {
                checkGuess(currentGameMovie);
            } else {
                Toast.makeText(requireContext(), "Generate a game movie first", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    @Override
    public void onMovieDataChanged() {
        // This method will be called when the movie data is loaded or updated
        Toast.makeText(requireContext(), "Movie data has been loaded/updated", Toast.LENGTH_SHORT).show();
        currentRandomMovie = movieRepository.getRandomMovie();
        if (currentRandomMovie != null) {
            updateRandomMovieUI(currentRandomMovie);
        }
        currentGameMovie = movieRepository.getRandomMovie();
        if (currentGameMovie != null) {
            setGameDetails(currentGameMovie);
        }
    }


    private void updateRandomMovieUI(DataClass randomMovie) {
        randomMovieTitle.setText(randomMovie.getTitle());
        randomMovieGenre.setText("Genre: " + randomMovie.getGenre());
        randomMovieYear.setText("Year: " + randomMovie.getYear());
        randomMovieRating.setText("Rating: " + randomMovie.getRating());
        Picasso.get().load(randomMovie.getPosterUrl()).into(randomMoviePoster);
    }

    private void setGameDetails(DataClass randomMovie) {
        gameRating.setText("Rating: " + randomMovie.getRating());
        gameYear.setText("Year: " + randomMovie.getYear());
        gameGenre.setText("Genre: " + randomMovie.getGenre());
        gameDescription.setText(randomMovie.getPlot());
        gameResult.setText("");
        gameMovieName.setText("");
    }

    private void checkGuess(DataClass randomMovie) {
        String userGuess = guessInput.getText().toString().trim();
        if (userGuess.equalsIgnoreCase(randomMovie.getTitle())) {
            gameResult.setText("Success! You guessed the correct movie.");
        } else {
            gameResult.setText("Failed. Try again!");
            gameMovieName.setText("The correct movie was: " + randomMovie.getTitle());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieRepository.removeObserver(this);
    }
}
