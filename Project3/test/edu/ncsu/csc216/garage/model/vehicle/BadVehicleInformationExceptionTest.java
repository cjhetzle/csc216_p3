package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * BadVehicleInformationExceptionTest Class
 * @author Cameron
 *
 */
public class BadVehicleInformationExceptionTest {

	/**
	 * testConstructor
	 */
	@Test
	public void testConstructor() {
		try {
			throw new BadVehicleInformationException();
		} catch (BadVehicleInformationException e) {
			assertEquals("Bad Vehicle Information", e.getMessage());
		}
		
		try {
			throw new BadVehicleInformationException("This is custom");
		} catch (BadVehicleInformationException e) {
			assertEquals("This is custom", e.getMessage());
		}
	}

}
