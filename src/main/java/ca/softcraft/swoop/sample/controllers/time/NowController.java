package ca.softcraft.swoop.sample.controllers.time;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NowController {
	@GetMapping("/time/now")
	NowResult get() {
		/*
		 * IMO, application-specific environment variables should be namespaced to avoid conflicts.
		 * The exception being environment variables that are shared across applications.
		 */
		String apiKey = System.getenv("SWOOP_SAMPLE_AMDOREN_API_KEY");
		if  (apiKey == null) {
			throw new RuntimeException("Environment variable SWOOP_SAMPLE_AMDOREN_API_KEY is not defined.");
		}
		AmdorenTimeZoneResult current = AmdorenTimeZoneResult.load(apiKey);
		return NowResult.fromTemporalAccessor(current.getTemporalAccessor(), current.getOffset() * 60);
	}
}
