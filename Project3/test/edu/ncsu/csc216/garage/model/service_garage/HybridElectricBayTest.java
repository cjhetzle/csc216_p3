package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
/**
 * tests the hybrid electric bay class
 * @author Marshall
 * @author Cameron Hetzler
 *
 */
public class HybridElectricBayTest {
	
	/**
	 * tests the occupy method
	 */
	@Test
	public void testOccupy() {
		HybridElectricBay sb = new HybridElectricBay();
		Vehicle v1 = null, v2 = null, v3 = null;
		try {
			v1 = new RegularCar("string", "string", 0);
			v2 = new HybridElectricCar("string1", "string1", 1);
			v3 = new HybridElectricCar("string2", "string2", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			sb.occupy(v1);
			fail();
		} catch (BayCarMismatchException | BayOccupiedException e) {
			//assertEquals("E01: EMPTY", sb.toString());
		}
		
		try {
			sb.occupy(v2);
			//assertEquals("E01: string1  string1", sb.toString());
		} catch (BayCarMismatchException | BayOccupiedException e) {
			fail();
		}
		
		try {
			sb.occupy(v3);
			fail();
		} catch (BayCarMismatchException | BayOccupiedException e) {
			//assertEquals("E01: string1  string1", sb.toString());
		}
	}

}
