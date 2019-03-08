package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * BayOccupiedExceptionTest class
 * @author Cameron
 *
 */
public class BayOccupiedExceptionTest {

	/**
	 * testConstructor
	 */
	@Test
	public void testConstructor() {
		try {
			throw new BayOccupiedException();
		} catch (BayOccupiedException e) {
			assertEquals("Bay is Occupied", e.getMessage());
		}
		
		try {
			throw new BayOccupiedException("This is custom");
		} catch (BayOccupiedException e) {
			assertEquals("This is custom", e.getMessage());
		}
	}

}
