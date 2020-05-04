package com.example.isc7424s12020_a1_1518687;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {
	private ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

	@Rule
	public ActivityScenarioRule<MainActivity> getActivityRule() {
		return this.activityRule;
	}

	@Test  // check the Intent is correct
	public void testActivityInView() {
		Espresso.onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));
	}

	@Test  // check the Buttons' visibility
	public void testVisibilityButton() {
		Espresso.onView(ViewMatchers.withId(R.id.makeAppBtn)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
		Espresso.onView(ViewMatchers.withId(R.id.summaryPersonalBtn)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
		Espresso.onView(ViewMatchers.withId(R.id.summaryOverallBtn)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
	}

	@Test  // Check the text match within the strings.xml
	public void testTitleTextDisplay() {
		Espresso.onView(ViewMatchers.withId(R.id.textView)).check(matches(ViewMatchers.withText(R.string.textView)));
		Espresso.onView(ViewMatchers.withId(R.id.textView2)).check(matches(ViewMatchers.withText(R.string.textView2)));
	}


}