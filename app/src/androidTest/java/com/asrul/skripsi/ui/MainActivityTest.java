package com.asrul.skripsi.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.asrul.skripsi.R;
import com.asrul.skripsi.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityRule.getActivity();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void alphabetTest() {
        onView(withId(R.id.btnAlphabet)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAlphabet)).perform(click());
    }

    @Test
    public void kataTest() {
        onView(withId(R.id.btnWord)).check(matches(isDisplayed()));
        onView(withId(R.id.btnWord)).perform(click());
        onView(withId(R.id.rvWord)).check(matches(isDisplayed()));
        onView(withId(R.id.rvWord)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void beritaTest() {
        onView(withId(R.id.rvBerita)).check(matches(isDisplayed()));
        onView(withId(R.id.rvBerita)).perform(RecyclerViewActions.scrollToPosition(3)).perform(click());
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
    }

    @Test
    public void profileTest() {
        onView(withId(R.id.profile_nav)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_nav)).perform(click());
        onView(withId(R.id.tvVersion)).check(matches(isDisplayed()));
    }
}