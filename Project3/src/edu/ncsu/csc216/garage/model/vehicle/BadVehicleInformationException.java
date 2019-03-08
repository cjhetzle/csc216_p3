package edu.ncsu.csc216.garage.model.vehicle;

/**
 * BadVehicleInformationException Class
 * @author Cameron
 * @author Marshall Skelton
 *
 */
@SuppressWarnings("serial")
public class BadVehicleInformationException extends Exception {

	/**
	 * Default constructor with pre-written message
	 */
	public BadVehicleInformationException() {
		super("Bad Vehicle Information");
	}
	
	/**
	 * accepts custom exception message
	 * @param message exception message
	 */
	public BadVehicleInformationException(String message) {
		super(message);
	}
}
