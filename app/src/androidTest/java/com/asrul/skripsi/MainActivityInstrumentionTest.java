package com.asrul.skripsi;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.asrul.skripsi.ui.MainActivity;
import com.asrul.skripsi.utils.EspressoIdlingResource;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentionTest {

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
    public void alphabetButtonTest() {
        onView(withId(R.id.btnAlphabet)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAlphabet)).perform(click());
    }

    @Test
    public void kataButtonTest() {
        onView(withId(R.id.btnWord)).check(matches(isDisplayed()));
        onView(withId(R.id.btnWord)).perform(click());
        onView(withId(R.id.rvWord)).check(matches(isDisplayed()));
        onView(withId(R.id.rvWord)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void beritaTest() {
        onView(withId(R.id.rvBerita)).check(matches(isDisplayed()));
        onView(withId(R.id.rvBerita)).perform(RecyclerViewActions.scrollToPosition(10));
    }

    @Test
    public void profileTest() {
        onView(withId(R.id.profile_nav)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_nav)).perform(click());
        onView(withId(R.id.tvVersion)).check(matches(isDisplayed()));
    }
}