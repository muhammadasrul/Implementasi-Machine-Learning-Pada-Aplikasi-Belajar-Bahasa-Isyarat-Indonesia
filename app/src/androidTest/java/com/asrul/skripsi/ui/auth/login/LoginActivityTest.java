package com.asrul.skripsi.ui.auth.login;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.asrul.skripsi.R;
import com.asrul.skripsi.AndroidTestUtils;
import com.asrul.skripsi.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity loginActivity = null;
    private AndroidTestUtils testUtils = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = activityTestRule.getActivity();
        testUtils = new AndroidTestUtils();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void performDontHaveAnAccountButton_navigateToRegister() {
        onView(withId(R.id.btnRegister)).perform(scrollTo()).perform(click());
        onView(withId(R.id.cvRegisterForm)).check(matches(isDisplayed()));
    }

    @Test
    public void loginWithEmailAndPassword() {
        testUtils.checkLogin();
        onView(withId(R.id.edtEmail)).perform(replaceText("test@gmail.com"));
        onView(withId(R.id.edtPassword)).perform(replaceText("test123"));
        onView(withId(R.id.btnLogin)).perform(click());
    }

    /**
     * not working yet!, try running on API 29 or lower (not tested)
     */

//    @Test
//    public void loginWithGoogle() {
//        onView(withId(R.id.btnLoginWithGoogle)).perform(click());
//        onView(withText("Choose an account"))
//                .inRoot(withDecorView(not(is(loginActivity.getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));
//        onView(withText("masrlapgst@gmail.com"))
//                .inRoot(withDecorView(not(is(loginActivity.getWindow().getDecorView()))))
//                .perform(click());
//    }
}