package ca.softcraft.swoop.sample.controllers.math;

/**
 * A class to encapsulate the operands for addition and calculate the total.
 * 
 * Is this a model class? There's a bit of mixing of concerns here. We need a
 * class to represent the structure of the request body of POST /math/add. Once
 * we have that, it's nice to move the "business logic" (the actual addition of
 * the operands) out of the controller (fitting the "skinny controllers"
 * principle) into some sort of model or service object. We already have
 * AddOperands, which is not *strictly* a model (it's closely tied to the format
 * of the POST request body). However, the business logic is so simple that I
 * don't think it's worth creating a whole other object just to perform an
 * addition; I'd rather just add the calculation method to this class and reuse
 * it for the GET request. Then I'll write this comment to discuss how this
 * isn't necessarily the "most-correct" approach for a real-world (and more
 * complex) system.
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
 */
public class AddOperands {
	private double _n1;
	private double _n2;

	/**
	 * @return The first operand
	 */
	public double getN1() {
		return _n1;
	}

	/**
	 * @return the second operand
	 */
	public double getN2() {
		return _n2;
	}

	public double getTotal() {
		return _n1 + _n2;
	}

	/**
	 * @param value
	 *            The value of the first operand
	 */
	public void setN1(double value) {
		_n1 = value;
	}

	/**
	 * @param value
	 *            The value of the second operand
	 */
	public void setN2(double value) {
		_n2 = value;
	}
}
