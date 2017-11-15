/**
 * 
 */
package ca.softcraft.swoop.sample.test.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.softcraft.swoop.sample.controllers.math.AddOperands;

class AddOperandsTest {
	private AddOperands _subject;

	@BeforeEach
	void setUp() throws Exception {
		_subject = new AddOperands();
	}

	/**
	 * Test method for {@link ca.softcraft.swoop.sample.controllers.math.AddOperands#getTotal()}.
	 */
	@Test
	void testGetTotal() {
		Random r = new Random();
		double n1 = r.nextDouble();
		_subject.setN1(n1);
		double n2 = r.nextDouble();
		_subject.setN2(n2);
		
		assertEquals(n1 + n2, _subject.getTotal());
	}

}
