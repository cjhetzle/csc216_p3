package edu.ncsu.csc216.garage.model.service_garage;
/**
 * BayOccupiedException Class
 * @author Cameron
 *
 */
@SuppressWarnings("serial")
public class BayOccupiedException extends Exception {
	
	/**
	 * Default constructor with prewritten message
	 */
	public BayOccupiedException() {
		super("Bay is Occupied");
	}
	
	/**
	 * accepts custom exception message
	 * @param message exception message
	 */
	public BayOccupiedException(String message) {
		super(message);
	}
}
