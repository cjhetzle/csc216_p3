package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * HybridElectricCar class
 * @author Cameron
 * @author Marshall Skelton
 *
 */
public class HybridElectricCar extends Vehicle {
	/**
	 * Hybrid vehicle constructor
	 * @param license the license of the driver
	 * @param name the name of the driver
	 * @param tierIndex the index
	 * @throws BadVehicleInformationException thrown for incorrect vehicle information
	 */
	public HybridElectricCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
		super(license, name, tierIndex);
	}
	/**
	 * iterates through the garage service bays and picks one that is not occupied and is a hybrid/electric service bay
	 * @param garage the garage for vehicle
	 * @throws NoAvailableBayException when the bay is full 
	 * @throws BayCarMismatchException 
	 * @throws BayOccupiedException 
	 */
	@Override
	public void pickServiceBay(Garage garage) throws NoAvailableBayException {
		if (garage.numberOfEmptyBays() == 0) {
			throw new NoAvailableBayException();
		} else {
			for (int i = garage.getSize() - 1;  i >= 0; i--) {
				ServiceBay sb = garage.getBayAt(i);
				if (!sb.isOccupied()) {
					try {
						sb.occupy(this);
					} catch (BayOccupiedException | BayCarMismatchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
			}
		}
	}
	/**
	 * String method
	 * @return the string of the electric car
	 */
	public String toString() {
		return "E " + super.toString();
	}
}
