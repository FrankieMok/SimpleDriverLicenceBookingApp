package com.example.isc7424s12020_a1_1518687;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnitTestHashMapTest {


	List<String> info1 = new ArrayList<String>();
	List<String> info2 = new ArrayList<String>();
	List<String> info3 = new ArrayList<String>();
	List<String> info4 = new ArrayList<String>();

	List<String> test1;
	String test2, test3;

	@Test
	public void checkArrayEmpty() {
		Map<String, List<String>> bookInfo = new HashMap<>();
		info1.add("Monday");
		info1.add("09:00 - 10:00");
		info2.add("Tuesday");
		info2.add("12:00 - 13:00");
		info3.add("Wednesday");
		info3.add("10:00 - 11:00");
		info4.add("Thursday");
		info4.add("11:00 - 12:00");
		bookInfo.put("NZ123456", info1);
		bookInfo.put("NZ234567", info2);
		bookInfo.put("NZ345678", info3);
		bookInfo.put("NZ456789", info4);

		test1 = bookInfo.get("NZ123456");
		test2 = test1.get(0);
		test3 = test1.get(1);

		assertThat(bookInfo.size(), is(4));
		assertThat(bookInfo, IsMapContaining.hasKey("NZ123456"));
		assertThat(bookInfo, IsMapContaining.hasEntry(equalTo("NZ123456"), equalTo(info1)));
		assertThat(bookInfo, IsMapContaining.hasValue(info4));
		assertThat(test1, is(info1));
		assertThat(test2, is("Monday"));
		assertThat(test3, is("09:00 - 10:00"));
	}

}
