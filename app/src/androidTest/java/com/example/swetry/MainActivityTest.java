package com.example.swetry;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.action.ViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testHomeFragmentUI() {
        activityRule.launchActivity(new Intent());

        //Click on the home item in the bottom navigation view to display HomeFragment
        onView(withId(R.id.home)).perform(click());

        // Check if the search view is displayed
        onView(withId(R.id.search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the TextView is displayed and has the correct text
        onView(withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("Recent Releases")));
    }

    @Test
    public void testShortsFragmentUI() {
        activityRule.launchActivity(new Intent());

        //Click on the shorts item in the bottom navigation view to display ShortsFragment
        onView(withId(R.id.shorts)).perform(click());

        // Check if the search view is displayed
        onView(withId(R.id.search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the TextView is displayed and has the correct text
        onView(withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("All Time Top")));
    }

}
