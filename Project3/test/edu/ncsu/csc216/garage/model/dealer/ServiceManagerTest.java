/**
 * 
 */
package edu.ncsu.csc216.garage.model.dealer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests Service Manager
 * @author Marshall Skelton
 * @author Cameron Hetzler
 */
public class ServiceManagerTest {
	/**
	 * test service manager method
	 * 
	 * **COMMENTED OUT TESTS UNTIL SERVICE MANAGER IS FULLY IMPLEMENTED**
	 */
	@Test
	public void testServiceManager() {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			fail();
		}
		ServiceManager manager = new ServiceManager(fileScanner);
		String guiDisplay =	"R Platinum  HI-01345  Rhyne, Lauren\n"
		+ "R Platinum  NC-DUR1   Nicholson, Henry\n"
		+ "E Platinum  NC-CAR300 Myers, Gina\n"
		+ "R Platinum  SC-RT098  Richards, Fiona\n"
		+ "E Gold      NC-5678   Emerson, Jane\n"
		+ "R Gold      VA-780912345Towler, Francis\n"
		+ "R Gold      NC-987Z11 Tetterton, James\n"
		+ "E Gold      FL-457653 Fidler, Paul\n"
		+ "R Gold      VIRG0122  Jones, Francis\n"
		+ "E Gold      0987-NC   Nelson, Richard\n"
		+ "R Gold      IL20987   Masters, Burt\n"
		+ "E Gold      FL-M3456  McKeel, Kenneth\n"
		+ "E Gold      CA-SD0987 Lane, Susan\n"
		+ "E Gold      ND-12345  Young, Blake\n"
		+ "R Gold      AL-03456  Barber, David\n"
		+ "R Gold      IO-WA987  Ledbetter, Jeanne\n"
		+ "E Gold      MA-0987   Wilson, Richard\n"
		+ "R Silver    VA-121A   Henderson, William\n"
		+ "R Silver    MN-20134  McKeel, Robyn\n"
		+ "R Silver    CA-A-*TAR Williams, James\n"
		+ "R Silver    SC-0I033  Smith, Amos\n"
		+ "R Silver    678431    Bell, Frank\n"
		+ "E Silver    WX-01292  Wall, Susan\n"
		+ "R Silver    NC-RAL0   Lamb, Bill\n"
		+ "E Silver    ND-34511  Young, Charlotte\n"
		+ "R Silver    TN-$23455 Lyons, Robert\n"
		+ "R Silver    SC-KPLEM22Ferguson, Paul\n"
		+ "R Silver    98234-RI  Bell, Richard\n"
		+ "R None      NC-122    Doe, John\n"
		+ "E None      PA-93P093300Littleton, Christine\n"
		+ "R None      NC-HAPPYCAMPERPage, Kimberly\n"
		+ "E None      FL-09876  Peterson, Keith\n"
		+ "R None      FL-A09833 Handrick, Marian\n"
		+ "R None      FL-U09833 Handrick, Donald\n"
		+ "R None      SD-0      Young, Charles\n"
		+ "R None      TN-3245   Lyons, Linda\n"
		+ "E None      ME-78653  Smith, Sandra\n"
		+ "E None      CN09822   Hughes, Jack\n"
		+ "E None      NC-1233   Doe, Baby John\n";
		assertEquals(guiDisplay, manager.printWaitList(""));
		guiDisplay =  "E Platinum  NC-CAR300 Myers, Gina\n"
		+ "R Gold      IL20987   Masters, Burt\n"
		+ "E Gold      FL-M3456  McKeel, Kenneth\n"
		+ "R Silver    MN-20134  McKeel, Robyn\n";
		assertEquals(guiDisplay, manager.printWaitList("m"));
		
		manager = new ServiceManager(null);
		
		assertNotNull(manager);
	}
	
	/**
	 * testRemoveFromWaitingList
	 */
	@Test
	public void testRemove() {
		ServiceManager manager = new ServiceManager();
		Vehicle v = null;
		try {
			v = new RegularCar("license", "name", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		manager.putOnWaitingList(v);
		
		assertEquals(v, manager.remove("", 0));
	}
	
	/**
	 * testFillServiceBays
	 */
	@Test
	public void testFillServiceBays() {
		ServiceManager manager = new ServiceManager();
		
		manager.putOnWaitingList("E", "1", "a", 0);
		manager.putOnWaitingList("E", "2", "b", 0);
		manager.putOnWaitingList("E", "3", "c", 0);
		
		manager.fillServiceBays();
		
		assertEquals("", manager.printWaitList(""));
	}
	
	/**
	 * testPutOnWaitingList
	 */
	@Test
	public void testPutOnWaitingList() {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			fail();
		}
		ServiceManager manager = new ServiceManager(fileScanner);
		Vehicle v = null;
		try {
			v = new RegularCar("HI-01345", "Rhyne, Lauren", 3);
		} catch (BadVehicleInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, manager.getWaitingItem("", 0).compareToTier(v));
		assertEquals(-1, manager.getWaitingItem("", 4).compareToTier(v));
		manager.putOnWaitingList(v);
		assertEquals(0, manager.getWaitingItem("", 4).compareToTier(v));
		
		manager = new ServiceManager();
		manager.putOnWaitingList(v);
		assertEquals(0, manager.getWaitingItem("", 0).compareToTier(v));
	}
	/**
	 * tests print method
	 */
	@Test
	public void testPrint() {
		ServiceManager s = new ServiceManager();
		assertEquals("108: EMPTY\n" +
		"106: EMPTY\n" +
		"105: EMPTY\n" +
		"103: EMPTY\n" + 
		"102: EMPTY\n" +
		"E01: EMPTY\n" +
		"E04: EMPTY\n" +
		"E07: EMPTY\n", s.printServiceBays());
	}
}