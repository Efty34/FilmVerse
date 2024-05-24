package com.example.swetry;

import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)

public class DetailActivityTest {

    private DataClass sampleMovie;

    @Rule
    public ActivityTestRule<DetailActivity> activityRule =
            new ActivityTestRule<>(DetailActivity.class, true, false);

    @Before
    public void setUp() {
        // Create a sample DataClass object
        sampleMovie = new DataClass("Sample Title", "9.0", "http://example.com/poster.jpg", "2024", "Action, Drama, Sci-Fi", "This is a sample plot.");

        // Create an intent with the sample movie data
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), DetailActivity.class);
        intent.putExtra("MovieData", sampleMovie);

        // Launch the activity with the intent
        activityRule.launchActivity(intent);
    }

    @Test
    public void testTitle() {
        // Check if the title is displayed correctly
        onView(withId(R.id.detailTitle))
                .perform(scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(sampleMovie.getTitle())));
    }

    @Test
    public void testRating() {
        // Check if the rating is displayed correctly
        onView(withId(R.id.detailRating))
                .perform(scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(sampleMovie.getRating())));
    }

    @Test
    public void testYear() {
        // Check if the year is displayed correctly
        onView(withId(R.id.detailYear))
                .perform(scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(sampleMovie.getYear())));
    }

}
