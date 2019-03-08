package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Service Bay class
 * @author Marshall Skelton
 * @author Cameron Hetzler
 *
 */
public class ServiceBay {
	/** occupied boolean for service */
	protected boolean occupied = false;
	/** vehicle for use */
	protected Vehicle myVehicle;
	/** bay id for construction */
	private String bayID;
	/** int next number */
	private static int nextNumber = 1;
	
	/**
	 * Resets the service bay numbering to start from 1
	 */
	public static void startBayNumberingAt101() {
		nextNumber = 1;
	}
	
	/**
	 * accepts a custom first character to the bayID
	 * @param type the type of service bay
	 */
	public ServiceBay(String type) {
		if (type == null) {
			bayID = "10" + nextNumber;
			nextNumber++;
			return;
		} 
		type = type.trim();
		if (type.length() == 0) {
			bayID = "10" + nextNumber;
			nextNumber++;
			return;
		} else {
			if (nextNumber < 10) {
				bayID = type.charAt(0) + "0" + nextNumber;
			} else {
				bayID = type + nextNumber;
			}
			nextNumber++;
		}
	}
	
	/**
	 * Default constructor
	 * Creates a new empty service bay according to the current bay numbering then increments that number
	 */
	public ServiceBay() {
		if (nextNumber < 10)
			bayID = "10" + nextNumber;
		else
			bayID = "1" + nextNumber;
		nextNumber++;
	}
	
	/**
	 * returns the ID of the service bay
	 * @return bayID String
	 */
	public String getBayID() {
		return bayID;
	}
	
	/**
	 * Returns true or false if the bay is occupied
	 * @return occupied boolean true or false
	 */
	public boolean isOccupied() {
		return occupied;
	}
	/**
	 * Returns reference to the released vehicle and sets myVehicle to null and occupied to false
	 * @return v Vehicle reference
	 */
	public Vehicle release() {
		occupied = false;
		Vehicle v = myVehicle;
		myVehicle = null;
		return v;
	}
	/**
	 * Occupies the service bay with the given vehicle. Throws a BayOccupiedException
	 * if the service bay is already occupied.
	 * @param v new vehicle
	 * @throws BayCarMismatchException 
	 * @throws Exception BayOccupiedException
	 */
	public void occupy(Vehicle v) throws BayOccupiedException, BayCarMismatchException {
		if (!this.occupied) {
			myVehicle = v;
			occupied = true;
		} else {
			throw new BayOccupiedException();
		}
	}
	
	/**
	 * creates a string representation of the ServiceBay class
	 * @return string of the service bays
	 */
	public String toString() {
		if (occupied) {
			return bayID + ": " + String.format("%-9s",  myVehicle.getLicense()) + myVehicle.getName();		
		} else {
			return bayID + ": " + "EMPTY";
		}
	}

}
