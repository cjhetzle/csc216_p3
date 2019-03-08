package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * BayCarMismatchExceptionTest
 * @author Cameron
 *
 */
public class BayCarMismatchExceptionTest {

	/**
	 * testConstructor
	 */
	@Test
	public void testConstructor() {
		try {
			throw new BayCarMismatchException();
		} catch (BayCarMismatchException e) {
			assertEquals("Bay Car Mismatch", e.getMessage());
		}
		
		try {
			throw new BayCarMismatchException("This is custom");
		} catch (BayCarMismatchException e) {
			assertEquals("This is custom", e.getMessage());
		}
	}

}
