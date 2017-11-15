package ca.softcraft.swoop.sample.controllers.math;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {
	/**
	 * Add two numbers submitted via a GET request.
	 * 
	 * "n1" and "n2" are the URL parameter names used in the requirements, so it
	 * makes some sense to preserve them throughout the application. However, there
	 * are better possibilities. The names "operands", "terms", or "addends" are
	 * more descriptive than "n". Also, it would be more flexible to take an array
	 * of terms rather than a fixed number of them. Ideally, these ideas would be
	 * used to propose changes the specification; since that's not possible here,
	 * we'll stick to the spec as written.
	 * 
	 * @param n1
	 *            The first number to add.
	 * @param n2
	 *            The second number to add.
	 * @return The total of the two numbers formatted as a human-friendly localized
	 *         string
	 */
	@GetMapping("/math/add")
	String get(@RequestParam double n1, @RequestParam double n2) {
		/*
		 * We're letting Spring/RequestParam deal with the data validation here;
		 * entering a non-double value should be handled & rejected by Spring. In a
		 * real-world app, that may not be sufficient; we probably want to return a
		 * standardized validation error response (with a specific HTTP code, headers,
		 * and JSON payload). For this sample app, I'm willing to just make a textual
		 * nod towards data validation, and let Spring do the actual work.
		 */
		AddOperands operands = new AddOperands();
		operands.setN1(n1);
		operands.setN2(n2);
		return addResponse(operands);
	}
	 
	/**
	 * Get the total of the operands and format the result for the response body.
	 * 
	 * @param n1
	 *            The first number to add.
	 * @param n2
	 *            The second number to add.
	 * @return The total of the two numbers formatted as a human-friendly localized
	 *         string
	 */
	private String addResponse(AddOperands operands) {
		double result = operands.getTotal();
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
