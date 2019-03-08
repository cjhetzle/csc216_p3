package edu.ncsu.csc216.garage.model.service_garage;

/**
 * BayCarMismatchException Class
 * @author Cameron
 * @author Marshall Skelton
 *
 */
@SuppressWarnings("serial")
public class BayCarMismatchException extends Exception {
	
	/**
	 * Default constructor with prewritten message
	 */
	public BayCarMismatchException() {	
		super("Bay Car Mismatch");
	}
	
	/**
	 * Accepts custom exception message
	 * @param message exception message
	 */
	public BayCarMismatchException(String message) {
		super(message);
	}
}
