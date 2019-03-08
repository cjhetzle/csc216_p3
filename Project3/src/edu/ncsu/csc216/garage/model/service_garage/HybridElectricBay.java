package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * hybrid/electric bay
 * @author Marshall Skelton
 * @author Cameron Hetzler
 *
 */
public class HybridElectricBay extends ServiceBay {
	/**
	 * default constructor
	 */
	public HybridElectricBay() {
		super("E");
	}
	/**
	 * occupy a service bay
	 * @param v the vehicle to be added to the service bay
	 * @throws BayOccupiedException 
	 */
	public void occupy(Vehicle v) throws BayOccupiedException, BayCarMismatchException {
		if (!v.toString().substring(0, 1).equals("E")) {
			throw new BayCarMismatchException();
		}

		super.occupy(v);
	}
}
