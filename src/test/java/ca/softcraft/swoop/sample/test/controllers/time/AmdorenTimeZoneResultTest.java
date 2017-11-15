
package ca.softcraft.swoop.sample.test.controllers.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.softcraft.swoop.sample.controllers.time.AmdorenTimeZoneResult;

class AmdorenTimeZoneResultTest {
	private AmdorenTimeZoneResult _result;

	@BeforeEach
	void setUp() throws Exception {
		_result = new AmdorenTimeZoneResult();
	}

	/**
	 * Test method for
	 * {@link ca.softcraft.swoop.sample.controllers.time.AmdorenTimeZoneResult#getTemporalAccessor()}.
	 */
	@Test
	void testGetTemporalAccessor() {
		ZonedDateTime expected = ZonedDateTime.now();
		DateTimeFormatter formatter = AmdorenTimeZoneResult.getDateTimeFormatter();
		String formatted = formatter.format(expected);
		_result.setTime(formatted);
		TemporalAccessor actual = _result.getTemporalAccessor();
		/*
		 * Each test should have only one assertion. However, the assertEquals() check
		 * fails when comparing the expected from the actual (due to having different
		 * implementations), even though they represent the same value. In a more
		 * permanent system, I'd create a custom assertion to check whether the dates &
		 * times on arbitrary TemporalAccessors are equivalent. For this project, I'll
		 * just write two assertions as a quick and valid check.
		 */
		assertEquals(expected.get(ChronoField.DAY_OF_YEAR), actual.get(ChronoField.DAY_OF_YEAR));
		assertEquals(expected.get(ChronoField.SECOND_OF_DAY), actual.get(ChronoField.SECOND_OF_DAY));
	}

	/**
	 * Test method for
	 * {@link ca.softcraft.swoop.sample.controllers.time.AmdorenTimeZoneResult#load(java.lang.String)}.
	 */
	@Test
	@Ignore
	void testLoad() {
		/*
		 * We don't want to test the load() method without first mocking out the call to
		 * the external API. Otherwise, our test suite would spam the API (not to
		 * mention take longer) and potentially get locked out. Mocking out the API call
		 * is not trivial, so for now, just make a note of our intention, ignore this
		 * test, and move on.
		 */
	}
}
