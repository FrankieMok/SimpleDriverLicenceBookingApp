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
public class SummaryOverallActivityTest {

	private ActivityScenarioRule<SummaryOverallActivity> activityRule = new ActivityScenarioRule<>(SummaryOverallActivity.class);

	@Rule
	public ActivityScenarioRule<SummaryOverallActivity> getActivityRule() {
		return this.activityRule;
	}

	@Test // check the Intent is correct
	public void testActivityInView() {
		Espresso.onView(ViewMatchers.withId(R.id.sumOverall)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

	@Test  // check the Buttons' visibility
	public void testVisibility() {
		Espresso.onView(ViewMatchers.withId(R.id.overallNum)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.stackChart)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.monBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.tueBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.wedBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.thuBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.friBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.overallBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

}
