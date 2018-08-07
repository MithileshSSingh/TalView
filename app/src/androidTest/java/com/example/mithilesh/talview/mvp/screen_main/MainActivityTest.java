package com.example.mithilesh.talview.mvp.screen_main;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.mithilesh.talview.R;
import com.example.mithilesh.talview.utils.IdelingResourceInstance;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void mainActivityTest() {

        CountingIdlingResource countingIdlingResource = IdelingResourceInstance.getInstance().getCountingIdlingResource();
        Espresso.registerIdlingResources(countingIdlingResource);

        ViewInteraction linearLayout = onView(allOf(withId(R.id.rootLayout), childAtPosition(childAtPosition(withId(R.id.rvPost), 2), 0), isDisplayed()));
        linearLayout.perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(allOf(withId(R.id.navigation_album), childAtPosition(childAtPosition(withId(R.id.navigation), 0), 1), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction linearLayout2 = onView(allOf(withId(R.id.rootLayout), childAtPosition(childAtPosition(withId(R.id.rvAlbum), 3), 0), isDisplayed()));
        linearLayout2.perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout3 = onView(allOf(withId(R.id.rootLayout), childAtPosition(childAtPosition(withId(R.id.recyclerView), 0), 0), isDisplayed()));
        linearLayout3.perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView2 = onView(allOf(withId(R.id.navigation_post), childAtPosition(childAtPosition(withId(R.id.navigation), 0), 0), isDisplayed()));
        bottomNavigationItemView2.perform(click());

    }
}
