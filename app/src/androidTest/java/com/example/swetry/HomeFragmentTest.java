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
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private List<DataClass> mockDataList;

    @Before
    public void setUp() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
        activityRule.launchActivity(intent);

        onView(withId(R.id.home)).perform(click());

        // Set up mock data
        setUpMockData();
    }

    private void setUpMockData() {
        mockDataList = new ArrayList<>();
        mockDataList.add(new DataClass("Parasite", "8.5/10", "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SX300.jpg", "2019", "Comedy, Drama, Thriller", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan."));
//        mockDataList.add(new DataClass("Sample Title", "9.0", "http://example.com/poster.jpg", "2024", "Action, Drama, Sci-Fi", "This is a sample plot."));
        // Add more mock data as needed
    }

    @Test
    public void testHomeFragmentUI() {
        onView(withId(R.id.search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("Recent Releases")));
    }

    @Test
    public void testRecyclerViewItemClick() {
        // Click on the first item in the RecyclerView
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check if the DetailActivity is opened and the title is displayed correctly
        DataClass firstMovie = mockDataList.get(0); // Using mock data

        onView(withId(R.id.detailTitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(firstMovie.getTitle())));

        onView(withId(R.id.detailRating))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(firstMovie.getRating())));

        onView(withId(R.id.detailYear))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(firstMovie.getYear())));

        onView(withId(R.id.detailPlot))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(firstMovie.getPlot())));

        // Check if the genres are displayed correctly
        String[] genres = firstMovie.getGenre().split(", ");
        onView(withId(R.id.genre1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(genres[0])));

        if (genres.length > 1) {
            onView(withId(R.id.genre2))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .check(ViewAssertions.matches(withText(genres[1])));
        }

        if (genres.length > 2) {
            onView(withId(R.id.genre3))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    .check(ViewAssertions.matches(withText(genres[2])));
        }
    }
}
