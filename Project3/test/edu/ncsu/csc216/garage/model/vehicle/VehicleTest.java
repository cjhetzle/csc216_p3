/**
 * 
 */
package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * tests the vehicle and vehicle lists along with the electric and regular cars
 * @author Marshall Skelton
 *
 */
public class VehicleTest {
	/**
	 * test the vehicle class
	 */
	@Test
	public void testVehicle() {
		Vehicle v1 = null;
		String license = "license", name = "Hetzler, Cameron";
		try {
			v1 = new RegularCar("				", "owner", 0);
		} catch (BadVehicleInformationException e) {
			assertEquals(null, v1);
		}
		try {
			v1 = new RegularCar("MA-1", "My Jeep", 1);
			assertTrue(v1.meetsFilter("my j "));
		} catch (BadVehicleInformationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			v1 = new RegularCar(license, name, 2);
			assertEquals(name, v1.getName());
			v1 = new RegularCar(license + " ", name + " ", 2);
			assertEquals(name, v1.getName());
			assertEquals(license, v1.getLicense());
			assertEquals("R Gold      license   Hetzler, Cameron", v1.toString());
			assertTrue(v1.meetsFilter("het"));
			assertTrue(v1.meetsFilter("HET"));
		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * testConstructor
	 */
	@Test
	public void testConstructor() {
		Vehicle v = null;
		try {
			v = new RegularCar(null, "name", 0);
		} catch (BadVehicleInformationException e) {
			assertNull(v);
		}
		
		try {
			v = new RegularCar("license", null, 0);
		} catch (BadVehicleInformationException e) {
			assertNull(v);
		}
		
		try {
			v = new RegularCar(" ", " ", 0);
		} catch (BadVehicleInformationException e) {
			assertNull(v);
		}
	}
	
	/**
	 * test the Hybrid electric class
	 */
	@Test
	public void testHybrid() {
		Vehicle hybrid = null;
		ServiceBay.startBayNumberingAt101();
		try {
			hybrid = new HybridElectricCar("XH3-4123", "Marshall Skelton", 3);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		assertEquals("E Platinum  XH3-4123  Marshall Skelton", hybrid.toString());
		
		try {
			hybrid.pickServiceBay(new Garage());
		} catch (NoAvailableBayException e) {
			assertEquals("E Platinum  XH3-4123  Marshall Skelton", hybrid.toString());
		}
	}
	
}
