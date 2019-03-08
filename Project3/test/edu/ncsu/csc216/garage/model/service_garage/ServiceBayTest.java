package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
/**
 * tests the service bay class
 * @author Marshall
 * @author Cameron Hetzler
 *
 */
public class ServiceBayTest {
	
	/**
	 * tests the occupy method
	 */
	@Test
	public void testOccupy() {
		ServiceBay sb = new ServiceBay();
		Vehicle v1 = null, v2 = null;
		try {
			v1 = new RegularCar("string", "string", 0);
			v2 = new RegularCar("string1", "string1", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		try {
			sb.occupy(v1);
			sb.occupy(v2);
		} catch (BayCarMismatchException | BayOccupiedException e) {
			//pass
		}
	}
	
	/**
	 * testToString
	 */
	@Test
	public void testToString() {
		Vehicle v = null;
		try {
			v = new RegularCar("license", "name", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb = new ServiceBay();
		
		try {
			sb.occupy(v);
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		
		assertEquals("101: license  name", sb.toString());
	}
}
