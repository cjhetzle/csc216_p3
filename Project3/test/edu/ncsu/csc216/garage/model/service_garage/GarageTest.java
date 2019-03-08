/**
 * 
 */
package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests the garage package
 * @author Marshall
 * @author Cameron Hetzler
 *
 */
public class GarageTest {
	
	/**
	 * tests the garage class along with the exceptions
	 */
	@Test
	public void testGarage() {
		Garage garage = new Garage();
		
		Vehicle v1 = null, v2 = null, v3 = null;
		try {
			v1 = new HybridElectricCar("123", "cam", 0);
			v2 = new HybridElectricCar("1234", "paul", 1);
			v3 = new HybridElectricCar("12", "pat", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			v1.pickServiceBay(garage);
			v2.pickServiceBay(garage);
			v3.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
	}
	
	/**
	 * tests initBays method
	 */
	@Test
	public void testInitBays() {
		Garage garage = new Garage();    
		Vehicle v1 = null, v2 = null, v3 = null; 
		try {
			v1 = new RegularCar("123", "cam", 0);
			v2 = new RegularCar("1234", "paul", 1);
			v3 = new HybridElectricCar("3", "c", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			v1.pickServiceBay(garage);
			v2.pickServiceBay(garage);
			v3.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
		
		assertTrue(garage.getBayAt(0).isOccupied());
		assertTrue(garage.getBayAt(1).isOccupied());
		assertTrue(garage.getBayAt(7).isOccupied());
		garage.getBayAt(0).release();
		garage.getBayAt(1).release();
		garage.getBayAt(2).release();
		assertFalse(garage.getBayAt(0).isOccupied());
		assertFalse(garage.getBayAt(1).isOccupied());
		assertFalse(garage.getBayAt(2).isOccupied());
	}
	
	/**
	 * tests number of empty bays
	 */
	@Test
	public void testNumberOfEmptyBays() {
		Garage garage = new Garage();
		
		Vehicle v1 = null, v2 = null, v3 = null, v4 = null;
		try {
			v1 = new RegularCar("1", "a", 2);
			v2 = new RegularCar("2", "b", 0);
			v3 = new HybridElectricCar("3", "c", 1);
			v4 = new HybridElectricCar("4", "d", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			v1.pickServiceBay(garage);
			v2.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
		
		assertEquals(6, garage.numberOfEmptyBays());
		
		ServiceBay.startBayNumberingAt101();
		garage = new Garage();
		
		try {
			v3.pickServiceBay(garage);
			v4.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
		
		assertEquals(6, garage.numberOfEmptyBays());
	}
	
	/**
	 * testOfAllTests
	 */
	@Test
	public void testOfAllTests() {
		Garage garage = new Garage();
		
		Vehicle v1 = null, v2 = null, v3 = null;
		try {
			v1 = new HybridElectricCar("1", "a", 2);
			v2 = new HybridElectricCar("2", "b", 0);
			v3 = new HybridElectricCar("3", "c", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			v1.pickServiceBay(garage);
			v2.pickServiceBay(garage);
			v3.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
		garage.release(7);
		garage.release(6);
		garage.release(5);
		Vehicle v4 = null, v5 = null, v6 = null;
		try {
			v4 = new HybridElectricCar("1", "a", 2);
			v5 = new RegularCar("2", "b", 0);
			v6 = new HybridElectricCar("3", "c", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			v4.pickServiceBay(garage);
			v5.pickServiceBay(garage);
			v6.pickServiceBay(garage);
		} catch (NoAvailableBayException e) {
			fail();
		}
	}
	
	/**
	 * testMaxServiceBays
	 */
	@Test
	public void testMaxServiceBays() {
		Garage garage = new Garage();
		
		for (int i = 0; i < 22; i++) {
			garage.addRepairBay();
		}
		assertTrue(garage.getBayAt(28)instanceof ServiceBay);
		assertTrue(garage.getBayAt(29) instanceof HybridElectricBay);
		assertEquals("E28: EMPTY", garage.getBayAt(29).toString());
	}
}
