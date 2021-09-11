package com.asrul.skripsi.ui.auth.register;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.asrul.skripsi.R;
import com.asrul.skripsi.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    private RegisterActivity registerActivity = null;

    @Before
    public void setUp() throws Exception {
        registerActivity = activityTestRule.getActivity();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        registerActivity = null;
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void performLoginAlreadyHaveAnAccountButton_navigateToLogin() {
        onView(withId(R.id.btnLogin)).perform(scrollTo()).perform(click());
        onView(withId(R.id.cvLoginForm)).check(matches(isDisplayed()));
    }

    @Test
    public void registerTest() {
        onView(withId(R.id.edtUsername)).perform(replaceText("Test"));
        onView(withId(R.id.edtEmail)).perform(replaceText("test@gmail.com"));
        onView(withId(R.id.edtPassword)).perform(replaceText("test123"));
        onView(withId(R.id.edtPassword2)).perform(replaceText("test123"));
        onView(withId(R.id.btnRegister)).perform(scrollTo()).perform(click());
    }
}