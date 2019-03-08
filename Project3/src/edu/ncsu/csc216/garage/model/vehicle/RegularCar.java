package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;
/**
 * Regular Car class
 * @author Marshall Skelton
 *
 */
public class RegularCar extends Vehicle {
	/**
	 * Regular car constructor
	 * @param license license of the owner
	 * @param name name of the owner
	 * @param tierIndex Tier index
	 * @throws BadVehicleInformationException thrown for incorrect vehicle information
	 */
	public RegularCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
		super(license, name, tierIndex);
	}
	/**
	 * iterates through the garage service bays and picks one that is not occupied and is a regular car
	 * @param garage the garage for vehicle
	 * @throws BayCarMismatchException adding wrong vehicle type with service bay type
	 * @throws BayOccupiedException a bay is occupied
	 * @throws NoAvailableBayException all bays are filled
	 */
	@Override
	public void pickServiceBay(Garage garage) throws NoAvailableBayException {
		if (garage.numberOfEmptyBays() == 0) {
			throw new NoAvailableBayException();
		} else {
			for (int i = 0; i < garage.getSize(); i++) {
				ServiceBay sb = garage.getBayAt(i);
				if (!sb.isOccupied() && sb.getBayID().charAt(0) == '1') {
					//occupys service bay 
					try {
						sb.occupy(this);
					} catch (BayOccupiedException | BayCarMismatchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				} else if (sb.getBayID().charAt(0) == 'E') {
						throw new NoAvailableBayException();
				}
			}
			
		}
	}
	/**
	 * string method for regular car
	 * @return the string of the regular car object
	 */
	public String toString() {
		return "R " + super.toString();
	}
	
}
