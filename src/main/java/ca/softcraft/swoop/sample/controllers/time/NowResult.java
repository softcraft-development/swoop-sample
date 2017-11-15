package ca.softcraft.swoop.sample.controllers.time;

/**
 *
 * The spec says "Return the result or the timestring in a rational JSON
 * document." So what is a "rational JSON document" for this result, which
 * inherently is just a time with a timezone? In my opintion, there are only two
 * real options:
 * 
 * 1. An single property with an ISO 8601 date/time/zone string. This is a
 * standardized and near-universal format. It handles time zones (technically,
 * UTC offsets) so no problems there. This would be my preferred option
 * generally. However, it doesn't *quite* fit the specs. If there's only a
 * single value, there's usually little need for a JSON document as a wrapper,
 * and the specs clearly want a JSON document. Furthermore, the specs say
 * "timezone and current time"; the "and" implies that these are supposed to be
 * separate properties.
 * 
 * 2. A JSON document where each component of the date, time, and zone is a
 * separate & atomic property. This means that there's no string parsing
 * necessary (beyond the parsing of the JSON document, which is required
 * regardless).
 * 
 * There's two more options that I reject:
 * 
 * 3. A combined date/time string as one property, and the time zone as the
 * other. This is the worst of both options above: it requires string parsing,
 * but isn't a standard format.
 * 
 * 4. A single time property that's the number of milliseconds past the epoch
 * (ie: Unix time), plus a separate time zone. This is technically workable, but
 * not human-readable, so it's not as desirable as option 1 or 2 above.
 * 
 * For a real-world app, Option 1 is most preferable; it's simple, easy to
 * implement, and easy to consume. However, the specs imply separate time & zone
 * fields are required. In a real-world situation, I'd absolutely ask for
 * clarification and suggest amending the specs to allow for Option 1. Since
 * that's not possible here, I'll just implement Option 2. (It's not *that* much
 * harder.)
 * 
 */
public class NowResult {
	/*
	 * Yes, we should probably consider what happens when years <1. ISO 8601 gets
	 * fuzzy with years before 1583, when the Gregorian calendar was adopted. We
	 * really should just be using ISO 8601 anyway, and years <1583 probably don't
	 * matter anyway, so for the purposes of this app I won't worry about it too
	 * much.
	 */
	private final int _year;
	/*
	 * Note that I'm defining this as the "common" month number (and ISO 8601
	 * standard month number) of January=1, instead of the Java internal standard of
	 * January=0.
	 */
	private final int _month;
	private final int _day;
	/*
	 * We're going to use a 24-hour clock for simplicity. We could change it to a
	 * 12-hour without too much difficulty. (But again, it should be ISO 8601
	 * anyway.)
	 */
	private final int _hour;
	private final int _minute;
	private final int _second;
	/*
	 * I'm not going to include milliseconds as it won't be accurate anyway; it'll
	 * take several milliseconds just to get the result back from the remote server;
	 */

	/*
	 * "Time Zones" aren't exactly the same as "UTC offsets". The latter are
	 * numbers; the former are names and/or location descriptors (ex: "MST" or
	 * "America/Denver"). Since the string descriptors are way more complex to
	 * process and far less useful without a way to calculate UTC offsets, I'm just
	 * going to stick with the UTC offsets.
	 * 
	 * Note that the UTC offset is in milliseconds, since that's what
	 * java.util.TimeZone uses. That's simple, but not terribly useful.
	 * 
	 * All of this would prompt discussions about the needs of the API (except that
	 * we'd probably just use ISO 8601, which defines everything).
	 */
	private final int _utcOffset;

	/**
	 * @param year
	 *            The year number
	 * @param month
	 *            The month of the year (1 through 12)
	 * @param day
	 *            The day of the month
	 * @param hour
	 *            The hour of the day (0 through 23)
	 * @param minute
	 *            The minute of the hour
	 * @param second
	 *            The second of the minute
	 */
	public NowResult(int year, int month, int day, int hour, int minute, int second, int utcOffset) {
		super();
		_year = year;
		_month = month;
		_day = day;
		_hour = hour;
		_minute = minute;
		_second = second;
		_utcOffset = utcOffset;
	}

	/**
	 * @return the year number
	 */
	public int getYear() {
		return _year;
	}

	/**
	 * @return The month of the year (1-12)
	 */
	public int getMonth() {
		return _month;
	}

	/**
	 * @return the day of the month
	 */
	public int getDay() {
		return _day;
	}

	/**
	 * @return the hour of the day (0-23)
	 */
	public int getHour() {
		return _hour;
	}

	/**
	 * @return the minute of the hour
	 */
	public int getMinute() {
		return _minute;
	}

	/**
	 * @return the second of the minute
	 */
	public int getSecond() {
		return _second;
	}

	/**
	 * @return the utcOffset
	 */
	public int getUtcOffset() {
		return _utcOffset;
	}
}
