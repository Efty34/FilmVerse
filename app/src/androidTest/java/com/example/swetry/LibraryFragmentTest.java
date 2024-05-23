package com.example.swetry;

import android.content.Intent;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LibraryFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        // Launch the MainActivity with LibraryFragment
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        activityRule.launchActivity(intent);

        // Navigate to LibraryFragment
        onView(withId(R.id.library)).perform(click());
    }

    @Test
    public void testLibraryFragmentUI() {
        // Check if the generate button is displayed
        onView(withId(R.id.generateButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie title TextView is displayed
        onView(withId(R.id.randomMovieTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie genre TextView is displayed
        onView(withId(R.id.randomMovieGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie year TextView is displayed
        onView(withId(R.id.randomMovieYear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie rating TextView is displayed
        onView(withId(R.id.randomMovieRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie poster ImageView is displayed
        onView(withId(R.id.randomMoviePoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testGenerateRandomMovie() {
        // Click the generate button
        onView(withId(R.id.generateButton)).perform(click());

        // Check if the random movie title TextView is updated
        onView(withId(R.id.randomMovieTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie genre TextView is updated
        onView(withId(R.id.randomMovieGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie year TextView is updated
        onView(withId(R.id.randomMovieYear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie rating TextView is updated
        onView(withId(R.id.randomMovieRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the random movie poster ImageView is updated
        onView(withId(R.id.randomMoviePoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}