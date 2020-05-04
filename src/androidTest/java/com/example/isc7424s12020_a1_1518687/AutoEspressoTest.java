package com.example.isc7424s12020_a1_1518687;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AutoEspressoTest {
	private static final String TAG = "AutoEspressoTest";

	private ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

	@Rule
	public ActivityScenarioRule<MainActivity> getActivityRule() {
		return this.activityRule;
	}

	@Test
	public void testNavMainActivity() {
		String licTestLong = "A123456789";
		String licTestNormal = "NZ123456";

		// Check empty input
		Espresso.onView(withId(R.id.textInputEditText)).check(matches(isDisplayed()));
		Espresso.onView(withId(R.id.makeAppBtn)).perform(click());
		Espresso.onView(withId(R.id.textInputEditText)).check(matches(hasTextInputLayoutErrorText("Field can't be empty!")));

		// Check the Driver Licence too long.
		Espresso.onView(withId(R.id.EditTextInput)).check(matches(isDisplayed()));
		Espresso.onView(withId(R.id.EditTextInput)).perform(ViewActions.typeText(licTestLong), closeSoftKeyboard());
		Espresso.onView(withId(R.id.makeAppBtn)).perform(click());
		Espresso.onView(withId(R.id.textInputEditText)).check(matches(hasTextInputLayoutErrorText("License too long!!")));

	}

		@Test
		public void testNavBookingActivity() {
			String licTestNormal = randomAlphaNumeric(8);

			// Check the Driver Licence is correct and Open BookActivity.class Intent
			Espresso.onView(withId(R.id.EditTextInput)).perform(clearText(), ViewActions.typeText(licTestNormal), closeSoftKeyboard());
			Espresso.onView(withId(R.id.makeAppBtn)).perform(click());
			Espresso.onView(withId(R.id.booking)).check(matches(isDisplayed()));

			// Check the Radio button is click.
			Espresso.onView(withId(R.id.licTextView)).check(matches(withText(licTestNormal)));
			Espresso.onView(withId(R.id.submitBtn)).check(matches(isDisplayed()));
			Espresso.onView(withId(R.id.submitBtn)).perform(click());
			Espresso.onView(withText("Missing information")).check(matches(isDisplayed())).perform(clickXY(300,600));
			Espresso.onView(withId(R.id.licTextView)).check(matches(withText(licTestNormal)));
			Espresso.onView(withId(R.id.rad1Btn5)).perform(click());
			Espresso.onView(withId(R.id.submitBtn)).perform(click());
			Espresso.onView(withText("Missing information")).check(matches(isDisplayed())).perform(clickXY(300,600));
			Espresso.onView(withId(R.id.rad2Btn6)).perform(click());
			Espresso.onView(withId(R.id.submitBtn)).perform(click());
			Espresso.onView(withText(R.string.bookingConfirm)).check(matches(isDisplayed()));
			Espresso.onView(withId(R.id.button_positive)).perform(click());
			Espresso.onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));

			// Check the related booking in SummaryPersonal is valid.
			Espresso.onView(withId(R.id.EditTextInput)).perform(clearText(), ViewActions.typeText(licTestNormal), closeSoftKeyboard());
			Espresso.onView(withId(R.id.summaryPersonalBtn)).perform(click());
			Espresso.onView(withId(R.id.licTextView)).check(matches(withText(licTestNormal)));
			Espresso.onView(withText("Friday")).check(matches(isDisplayed()));
			Espresso.onView(withText("14:00 - 15:00")).check(matches(isDisplayed()));
			Espresso.pressBack();
			Espresso.onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));
		}

		@Test
		public void testNavSummaryOverallActivity() {

			Espresso.onView(withId(R.id.summaryOverallBtn)).check(matches(isDisplayed()));
			Espresso.onView(withId(R.id.summaryOverallBtn)).perform(click());
			Espresso.onView(withId(R.id.sumOverall)).check(matches(isDisplayed()));
			Espresso.onView(withId(R.id.monBtn)).perform(click());
			Espresso.onView(withId(R.id.tueBtn)).perform(click());
			Espresso.onView(withId(R.id.wedBtn)).perform(click());
			Espresso.onView(withId(R.id.thuBtn)).perform(click());
			Espresso.onView(withId(R.id.friBtn)).perform(click());
			Espresso.onView(withId(R.id.overallBtn)).perform(click());
			Espresso.pressBack();
			Espresso.onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));

	}


	public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
		return new TypeSafeMatcher<View>() {

			@Override
			public void describeTo(Description description) { }

			@Override
			public boolean matchesSafely(View view) {
				if (!(view instanceof TextInputLayout)) {
					return false;
				}
				CharSequence error = ((TextInputLayout) view).getError();
				if (error == null)
					return false;
				String hint = error.toString();
				return expectedErrorText.equals(hint); }


		};
	}

	public static ViewAction clickXY(final int x, final int y) {
		return new GeneralClickAction(
				Tap.SINGLE,
				new CoordinatesProvider() {
					@Override
					public float[] calculateCoordinates( View view ){

						final int[] screenPos = new int[2];
						view.getLocationOnScreen(screenPos);

						final float screenX = screenPos[0] + x;
						final float screenY = screenPos[1] + y;

						return new float[]{screenX, screenY};

					}
				},
				Press.FINGER);
		 }

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
}
