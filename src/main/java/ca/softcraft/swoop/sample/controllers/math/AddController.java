package ca.softcraft.swoop.sample.controllers.math;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {
	/**
	 * Add two numbers together and return the result.
	 * 
	 * "n1" and "n2" are the URL parameter names used in the requirements, so it
	 * makes some sense to preserve them throughout the application. However, there
	 * are better possibilities. The names "operands", "terms", or "addends" are
	 * more descriptive than "n". Also, it would be more flexible to take an array
	 * of terms rather than a fixed number of them. Ideally, these ideas would be
	 * used to propose changes the specification; since that's not possible here,
	 * we'll stick to the spec as written.
	 * 
	 * There's no specs for the qualities/types of the "numbers" that we're adding.
	 * The URL parameters are inherently Strings, and so could be parsed/converted
	 * any way we like. There's no specs on the output type, so for simplicity and
	 * minimalism I'm just returning it as the raw result of the operation;
	 * ultimately this gets converted down to a String (really, a byte array with an
	 * encoding).
	 * 
	 * Typically, APIs deal with JSON documents (and the specs for the /time/now API
	 * request a "rational JSON document"). All we're doing with /math/add is
	 * calculating a result for two numbers, so there's not much point to returning
	 * anything but the total. A JSON wrapper around this value is certainly
	 * possible, but not required, so I'll omit it here.
	 * 
	 * If we were to use a JSON wrapper, it would in turn imply a JSON Number as the
	 * type of both operands and the total. JSON Numbers allow decimal points and do
	 * not have any fixed length or precision. For that reason, Java Doubles aren't
	 * perfect matches (they're IEE 754 floating-point numbers, and thus can't
	 * represent every decimal expansion, plus they have limits on size and
	 * precision).
	 * 
	 * The Jackson JSON serialization library used by Spring has a configuration
	 * setting USE_BIG_DECIMAL_FOR_FLOATS; this seems like it could be useful if the
	 * numbers we get turn out to exceed the capabilities of Java Doubles. See:
	 * https://github.com/FasterXML/jackson-databind/wiki/Deserialization-Features.
	 * 
	 * However, since this is ultimately a very simple sample test, I'll just
	 * Doubles as the type (which happens to be the Jackson default for JSON
	 * Numbers). In a real-world app I'd get clarification on the API requirements.
	 * 
	 * @param n1
	 *            The first number to add.
	 * @param n2
	 *            The second number to add.
	 * @return The total of the two numbers
	 */
	@RequestMapping(path = "/math/add", method = RequestMethod.GET)
	String get(@RequestParam double n1, @RequestParam double n2) {
		/*
		 * We're letting Spring/RequestParam deal with the data validation here;
		 * entering a non-double value should be handled & rejected by Spring. In a
		 * real-world app, that may not be sufficient; we probably want to return a
		 * standardized validation error response (with a specific HTTP code, headers,
		 * and JSON payload). For this sample app, I'm willing to just make a textual
		 * nod towards data validation, and let Spring do the actual work.
		 */
		double result = n1 + n2;
		/*
		 * Localization / internationalization is a big & important issue. I prefer to
		 * build it in at the start rather than try to retrofit it later.
		 * 
		 * How we determine the user's locale is a topic that warrants some
		 * investigation and specification (and should almost certainly involve the
		 * Accept-Language header), but Spring's built-in resolver is a great start, and
		 * good enough for this app.
		 * 
		 * You can see the localization in action if you switch your Accept-Language
		 * HTTP request header to something different than the default. For example, if
		 * you change it to "fr" and add 1.5 to 10,000, you'd get "10 001,5"
		 * 
		 */
		Locale locale = LocaleContextHolder.getLocale();
		/*
		 * As with everything else, the specific number format we use should be set by
		 * the spec. The standard NumberFormat instance will do nicely here; it only
		 * adds decimal places when necessary, and adds thousands-separators (ie:
		 * commas) when applicable for easy human-reading. (Of course, if the API was
		 * meant to be consumed by a machine, we might skip the comma separators and
		 * localization for easier parsing.)
		 */
		NumberFormat format = NumberFormat.getNumberInstance(locale);
		return format.format(result);
	}
}
