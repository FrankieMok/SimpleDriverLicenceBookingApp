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
public class SummaryPersonalActivityTest {

	private ActivityScenarioRule<SummaryPersonalActivity> activityRule = new ActivityScenarioRule<>(SummaryPersonalActivity.class);

	@Rule
	public ActivityScenarioRule<SummaryPersonalActivity> getActivityRule() {
		return this.activityRule;
	}

	@Test // check the Intent is correct
	public void testActivityInView() {
		Espresso.onView(ViewMatchers.withId(R.id.sumPersonal)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

	@Test  // check the visibility
	public void testVisibility() {
		Espresso.onView(ViewMatchers.withId(R.id.summaryListView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.imageView3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
		Espresso.onView(ViewMatchers.withId(R.id.licTextView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

}
