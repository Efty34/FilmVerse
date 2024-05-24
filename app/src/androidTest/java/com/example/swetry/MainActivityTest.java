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

    @Test
    public void testSubscriptionFragmentUI() {

        activityRule.launchActivity(new Intent());

        // Click on the subscriptions item in the bottom navigation view to display SubscriptionFragment
        onView(withId(R.id.subscriptions)).perform(click());

        // Check if the search view is displayed
        onView(withId(R.id.search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the first TextView (Biography) is displayed and has the correct text
        onView(withId(R.id.textView1)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Biography")));

        // Check if the first RecyclerView (Biography) is displayed
        onView(withId(R.id.recyclerView1)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the second TextView (Crime) is displayed and has the correct text
        onView(withId(R.id.textView2)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Crime")));

        // Check if the second RecyclerView (Crime) is displayed
        onView(withId(R.id.recyclerView2)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Continue similar checks for other sections...

        // Check if the third TextView (Adventure) is displayed and has the correct text
        onView(withId(R.id.textView3)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Adventure")));

        // Check if the third RecyclerView (Adventure) is displayed
        onView(withId(R.id.recyclerView3)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the fourth TextView (Action) is displayed and has the correct text
        onView(withId(R.id.textView4)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Action")));

        // Check if the fourth RecyclerView (Action) is displayed
        onView(withId(R.id.recyclerView4)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the fifth TextView (Romance) is displayed and has the correct text
        onView(withId(R.id.textView5)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Romance")));

        // Check if the fifth RecyclerView (Romance) is displayed
        onView(withId(R.id.recyclerView5)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the sixth TextView (Thriller) is displayed and has the correct text
        onView(withId(R.id.textView6)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("|  Thriller")));

        // Check if the sixth RecyclerView (Thriller) is displayed
        onView(withId(R.id.recyclerView6)).perform(ViewActions.scrollTo())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testLibraryFragmentUI() {
        activityRule.launchActivity(new Intent());

        // Click on the library item in the bottom navigation view to display LibraryFragment
        onView(withId(R.id.library)).perform(click());

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

}
