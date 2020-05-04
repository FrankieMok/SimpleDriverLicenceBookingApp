package com.example.isc7424s12020_a1_1518687;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class UnitTestISCG7424S1 {


		@Test
		public void checkArrayEmpty () {
			ArrayList<String> check = timeOfText();
			assertFalse(String.valueOf(check.isEmpty()), false);
			assertTrue(check.contains("09:00 - 10:00"));
			assertTrue(check.size() >= 8);
			assertFalse(check.size() <7);
		}

		@Test
		public void testWithHamcrest() {
			List<String> check = timeOfText();
			assertThat(check, (hasItems("09:00 - 10:00", "10:00 - 11:00")));
			assertThat(check, allOf(hasSize(greaterThan(3)), hasSize(lessThan(12))));
		}

		@Test
			public void testAdder() {
			assertEquals(15, sumDayValues());
		}

		@Test
			public void testRandomNum() {
			String check = randomAlphaNumeric(8);
			assertNotNull(check);
			assertEquals(8, check.length());
		}


	private ArrayList<String> timeOfText() {
		ArrayList<String> timeText = new ArrayList<>();
		timeText.add("09:00 - 10:00");
		timeText.add("10:00 - 11:00");
		timeText.add("11:00 - 12:00");
		timeText.add("12:00 - 13:00");
		timeText.add("13:00 - 14:00");
		timeText.add("14:00 - 15:00");
		timeText.add("15:00 - 16:00");
		timeText.add("16:00 - 17:00");
		return timeText;
	}

	private int sumDayValues() {
		Integer[] dayData = new Integer[5];
		dayData[0] = 1;
		dayData[1] = 2;
		dayData[2] = 3;
		dayData[3] = 4;
		dayData[4] = 5;
		int sumValue = 0;
		for (int i = 0; i < dayData.length;i++ ) {
			sumValue = sumValue + dayData[i];
		}
		return sumValue;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0 ) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

}