package edu.ncsu.csc216.garage.model.dealer;

import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
import edu.ncsu.csc216.garage.model.vehicle.VehicleList;

/**
 * Servicemanager Class
 * @author Cameron
 * @author Marshall Skelton
 *
 */
public class ServiceManager implements Manageable {
	/** garage object for use in service manager */
	private Garage myGarage;
	/** list of vehicles waiting to be helped */
	private VehicleList waitingCars;
	/**
	 * Default constructor
	 */
	public ServiceManager() {
		myGarage = new Garage();
		waitingCars = new VehicleList();
	}
	
	/**
	 * Accepts a custom scanner
	 * @param input the scanner used for service manager
	 */
	public ServiceManager(Scanner input) {
		myGarage = new Garage();
		if (input == null) {
			waitingCars = new VehicleList();
		} else {
			waitingCars = new VehicleList(input);
		}
	}
	
	/**
	 * fills all available service bays
	 */
	public void fillServiceBays() {
		int i = 0;
		while (myGarage.numberOfEmptyBays() > 0) {
			try {
				Vehicle v = waitingCars.get("", i);
				if (v == null) return;
				v.pickServiceBay(myGarage);
				waitingCars.remove("", i);
			} catch (NoAvailableBayException e) {
				i++;
			}
		}
	}
	
	/**
	 * remove a Tier
	 * @param filter a string
	 * @param index the index
	 * @return the Tier 
	 */
	public Tiered remove(String filter, int index) {
		return waitingCars.remove(filter, index);
	}
	
	/**
	 * Tiered release from service bay
	 * @param bayID the id of the service bay
	 * @return the Tier
	 */
	public Tiered releaseFromService(int bayID) {
		return myGarage.release(bayID);
	}
	
	/**
	 * add a new bay to service manager
	 */
	public void addNewBay() {
		myGarage.addRepairBay();
	}
	
	/**
	 * prints the wait list
	 * @param filter filters the waitlist
	 * @return a string
	 */
	public String printWaitList(String filter) {
		return waitingCars.filteredList(filter);
	}
	
	/**
	 * prints the service bays
	 * @return a string of service bays
	 */
	public String printServiceBays() {
		String data = "";
		for (int i = 0; i < myGarage.getSize(); i++) {
			data += myGarage.getBayAt(i).toString() + "\n";
		}
		return data;
	}
	
	/**
	 * Puts an item in the service wait list
	 * @param t type of item
	 * @param b item identifier (license)
	 * @param c item name (name of owner)
	 * @param x item priority (0 none, 1 silver, 2 gold, 3 platinum)
	 */
	public void putOnWaitingList(String t, String b, String c, int x) {
		Vehicle v = null;
		switch (t) {
		case "R": try {	v = new RegularCar(b, c, x); } catch (BadVehicleInformationException e) { /** */ } break;
		case "E": try {	v = new HybridElectricCar(b, c, x);	} catch (BadVehicleInformationException e) { /** */ } break;
		default: /** */ break;
		}
		if (v == null) {
			return;
		} else {
			waitingCars.add(v);
		}
	}
	
	/**
	 * adds a vehicle to the waiting list
	 * @param v the Tier
	 */
	public void putOnWaitingList(Tiered v) {
		Vehicle newV = (Vehicle) v;
		waitingCars.add(newV);
	}
	
	/**
	 * get the waiting item
	 * @param filter a string
	 * @param index the index
	 * @return the Tier
	 */
	public Tiered getWaitingItem(String filter, int index) {
		return waitingCars.get(filter, index);
	}
}
