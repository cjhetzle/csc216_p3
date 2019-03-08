package edu.ncsu.csc216.garage.model.service_garage;

/**
 * NoAvailableBayException Class
 * @author Cameron
 *
 */
@SuppressWarnings("serial")
public class NoAvailableBayException extends Exception {

	/**
	 * Default constructor with prewritten message
	 */
	public NoAvailableBayException() {
		super("There is no available bay");
	}
	
	/**
	 * accepts custom exception message
	 * @param message exception message
	 */
	public NoAvailableBayException(String message) {
		super(message);
	}
}
