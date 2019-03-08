package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * NoAvailableBayException
 * @author Cameron
 *
 */
public class NoAvaiableBayExceptionTest {

	/**
	 * testConstructor
	 */
	@Test
	public void testConstructor() {
		try {
			throw new NoAvailableBayException();
		} catch (NoAvailableBayException e) {
			assertEquals("There is no available bay", e.getMessage());
		}
		
		try {
			throw new NoAvailableBayException("This is custom");
		} catch (NoAvailableBayException e) {
			assertEquals("This is custom", e.getMessage());
		}
	}
}
