package ca.softcraft.swoop.sample.controllers.time;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import org.apache.http.client.fluent.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AmdorenTimeZoneResult {
	private String _time;
	private int _offset;

	/**
	 * The time string returned by Amdoren's Time Zone API.
	 * 
	 * @return The local time string.
	 */
	public String getTime() {
		return _time;
	}

	/**
	 * Parse the time string based on the date & time format.
	 * 
	 * @return a TemporalAccessor for the given time string.
	 */
	public TemporalAccessor getTemporalAccessor() {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		TemporalAccessor temporalAccessor = ofPattern.parse(getTime());
		return temporalAccessor;
	}

	/**
	 * Set the time string.
	 * 
	 * @param time
	 *            The time string from the API.
	 */
	public void setTime(String time) {
		_time = time;
	}

	/**
	 * The UTC Offset returned by Amdoren's Time Zone API.
	 * 
	 * @return The offset in minutes.
	 */
	public int getOffset() {
		return _offset;
	}

	/**
	 * Set the UTC Offset
	 * 
	 * @param offset
	 *            The offset (in minutes) to set
	 */
	public void setOffset(int offset) {
		_offset = offset;
	}

	/**
	 * Call the Amdoren TimeZone API for Calgary and return an AmdorenTimeZoneResult.
	 * 
	 * @param apiKey
	 *            The registered API key for Amdoren.
	 * @return The AmdorenTimeZoneResult containing the result of the API call.
	 */
	public static AmdorenTimeZoneResult load(String apiKey) {
		String url = String.format("https://www.amdoren.com/api/timezone.php?api_key=%s&loc=Calgary", apiKey);
		String json;
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException exception) {
			throw new RuntimeException("Error making Amdoren API call", exception);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		AmdorenTimeZoneResult amdorenTimeZoneResult;
		try {
			amdorenTimeZoneResult = objectMapper.readValue(json, AmdorenTimeZoneResult.class);
		} catch (IOException exception) {
			throw new RuntimeException("Error parsing Amdoren API response", exception);
		}
		return amdorenTimeZoneResult;
	}
}
