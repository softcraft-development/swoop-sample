package ca.softcraft.swoop.sample.controllers.time;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * A controller for getting the current time, as per the spec.
 * 
 * Note: The spec said that it "should fetch time for MST at time of call from
 * another service" and suggested https://www.developer.aero/WaitTime-API/Try-
 * it-Now as a sample service. Unfortunately, developer.aero wouldn't let me
 * create an API key to use the service, so it wasn't possible to integrate with
 * it. Instead I found another service that would return the current local time
 * and time zone for a given location.
 */
public class NowController {
	@GetMapping("/time/now")
	NowResult get() {
		/*
		 * IMO, application-specific environment variables should be namespaced to avoid
		 * conflicts. The exception being environment variables that are shared across
		 * applications.
		 */
		String apiKey = System.getenv("SWOOP_SAMPLE_AMDOREN_API_KEY");
		if (apiKey == null) {
			throw new RuntimeException("Environment variable SWOOP_SAMPLE_AMDOREN_API_KEY is not defined.");
		}
		AmdorenTimeZoneResult current = AmdorenTimeZoneResult.load(apiKey);
		return NowResult.fromTemporalAccessor(current.getTemporalAccessor(), current.getOffset() * 60);
	}
}
