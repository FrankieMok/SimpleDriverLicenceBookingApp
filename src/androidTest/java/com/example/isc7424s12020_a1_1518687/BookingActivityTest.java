package com.example.isc7424s12020_a1_1518687;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class BookingActivityTest {
	private ActivityScenarioRule<BookingActivity> activityRule = new ActivityScenarioRule<>(BookingActivity.class);

	@Rule
	public ActivityScenarioRule<BookingActivity> getActivityRule() {
		return this.activityRule;
	}

	@Test  // check the Intent is correct
	public void testActivityInView() {
		Espresso.onView(ViewMatchers.withId(R.id.booking)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

	@Test  // check the Buttons' visibility
	public void testVisibilityButton() {
		Espresso.onView(ViewMatchers.withId(R.id.submitBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		}


}