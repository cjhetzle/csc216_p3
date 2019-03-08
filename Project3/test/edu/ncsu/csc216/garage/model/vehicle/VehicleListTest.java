package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * VehicleListTest Class
 * @author Cameron
 *
 */
public class VehicleListTest {

	/**
	 * testVehicleList
	 */
	@Test
	public void testVehicleList() {
		VehicleList list = new VehicleList();
		
		try {
			RegularCar v1 = new RegularCar("123", "cam", 0);
			RegularCar v2 = new RegularCar("1234", "rob", 1);
			RegularCar v3 = new RegularCar("12345", "pat", 2);
			RegularCar v4 = new RegularCar("12", "poor", 0);
			list.add(v1);
			list.add(v2);
			list.add(v3);
			assertEquals("cam", list.get("cam", 0).getName());
			assertEquals("pat", list.get("", 0).getName());
			assertEquals("rob", list.get("", 1).getName());
			assertEquals("cam", list.get("", 2).getName());
			list.remove("", 0);
			assertEquals("cam", list.get("", 1).getName());
			assertEquals("rob", list.get("", 0).getName());
			list.add(v4);
			assertEquals("poor", list.get("", 2).getName());
			list.add(v3);
			list.add(v2);
			assertEquals("rob", list.get("", 2).getName());
			list.remove("", 1);
			list.remove("", 2);
			list.remove("", 1);
			list.remove("", 0);
			list.remove("", 0);
			list.add(v1);
			assertEquals("R None      123       cam\n", list.filteredList(""));
		} catch (BadVehicleInformationException e) {
			fail();
		}		
	}
	
	/**
	 * testFilteredList
	 */
	@Test
	public void testFilteredList() {
		VehicleList list = new VehicleList();
		
		String filteredList = "";
		filteredList = list.filteredList("");
		
		assertNotNull(filteredList);
		assertEquals("", filteredList);
		
		Vehicle v1 = null;
		try {
			v1 = new RegularCar("license", "name", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		list.add(v1);
		filteredList = list.filteredList("");
		assertNotNull(filteredList);
		assertEquals("R Silver    license   name\n", filteredList);
		
	}

}
