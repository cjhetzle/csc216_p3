package edu.ncsu.csc216.garage.model.vehicle;

import java.util.Scanner;
import edu.ncsu.csc216.garage.model.service_garage.Garage;

import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
/**
 * Vehicle Class implements Tiered Interface
 * @author Cameron
 * @author Marshall Skelton
 *
 */
public abstract class Vehicle implements Tiered {
	/** string license for constructor */
	private String license;
	/** name of the owner */
	private String name;
	/** int tier index */
	private int tierIndex;
	/** string array */
	public static final String[] CUSTOMER_TIER = null;
	
	/**
	 * constructor with license, owner and status
	 * @param license Vehicles license plate
	 * @param owner Owners name (last, first)
	 * @param status Priority tier index
	 * @throws BadVehicleInformationException  thrown for incorrect information
	 */
	public Vehicle(String license, String owner, int status) throws BadVehicleInformationException {
		setLicense(license);
		setName(owner);
		setTier(status);
	}
	/**
	 * constructor with only information and status
	 * @param info information
	 * @param status status of the car
	 * @throws BadVehicleInformationException if incorrect Tiered information
	 */
	public Vehicle(String info, int status) throws BadVehicleInformationException {
		Scanner read = new Scanner(info);
		setLicense(read.next());
		setName(read.next());
		read.close();
		setTier(status);
	}
	/**
	 * pick service bay method
	 * @param garage garage for added vehicle service bay
	 * @throws NoAvailableBayException thrown if no open bay
	 */
	public abstract void pickServiceBay(Garage garage) throws NoAvailableBayException;
	
	/**
	 * meets filter method
	 * @param filter the string
	 * @return boolean if the filter meets requirements
	 */
	public boolean meetsFilter(String filter) {
		if (filter == null) 
			return true;
		else {
			filter = filter.trim();
			filter = filter.toLowerCase();
			String parse = name.toLowerCase().substring(0, filter.length());
			return filter.equals(parse);
		}
	}
	
	/**
	 * String method
	 * @return string of the vehicle
	 */
	public String toString() {
		String serviceTier = "";
		switch (tierIndex) {
		case 0:
			serviceTier = "None";
			break;
		case 1:
			serviceTier = "Silver";
			break;
		case 2:
			serviceTier = "Gold";
			break;
		case 3:
			serviceTier = "Platinum";
			break;
		default:
			serviceTier = "None";
			break;	
		}
		return String.format("%-10s", serviceTier) + String.format("%-10s", license) + name;
	}
	
	/**
	 * retrieves the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * retrieves the license 
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	
	/**
	 * retrieves the Tier of the vehicle
	 * @return the tier index
	 */
	public int getTier() {
		return tierIndex;
	}
	
	/**
	 * Compares the tier status of either object
	 * returns 0 if both tiers are the same
	 * returns -1 if this tier is less than the other
	 * returns +1 if this tier is greater than the other
	 * @param t Tiered being compared
	 * @return int Positive if greater, Negative if less, 0 if equal
	 */
	public int compareToTier(Tiered t) {
		int t2 = t.getTier();
		if (tierIndex == t2) {
			return 0;
		} else if (tierIndex < t2) {
			return -1;
		}
		return 1;
	}
	
	/**
	 * sets the tier object
	 * @param tier tier index
	 * @throws BadVehicleInformationException thrown for incorrect vehicle information
	 */
	public void setTier(int tier) throws BadVehicleInformationException {
		if (tier >= 0 && tier <= 3) {
			tierIndex = tier;
		} else {
			throw new BadVehicleInformationException("Invalid tier.");
		}
	}
	
	/**
	 * sets the name
	 * @param n name 
	 * @throws BadVehicleInformationException thrown for incorrect vehicle information
	 */
	private void setName(String n) throws BadVehicleInformationException {
		if (n == null) {
			throw new BadVehicleInformationException();
		} else {
			name = n.trim();
			if (onlySpace(name) || name.length() == 0) {
				throw new BadVehicleInformationException("Owner name cannot be blank.");
			}
		}
	}
	
	/**
	 * sets the license 
	 * @param l string of the license
	 * @throws BadVehicleInformationException thrown for incorrect vehicle information
	 */
	private void setLicense(String l) throws BadVehicleInformationException {
		if (l == null) {
			throw new BadVehicleInformationException("License cannot be blank.");
		} else if (onlySpace(l)) {
			throw new BadVehicleInformationException();
		} else {
			license = l.trim();
			if (licenseSpace(license) || license.length() == 0) {
				throw new BadVehicleInformationException("License cannot be blank.");
			}
		}
	}
	/**
	 * private method checks if the input is only spaces
	 * @param space string checked for only spaces
	 * @return boolean whether it is a string with only spaces
	 */
	private boolean onlySpace(String space) {
		boolean wow = false;
		for (int i = 0; i < space.length(); i++) {
			if (space.charAt(i) == ' ') {
				wow = true;
			} else {
				return false;
			}
		}
		return wow;
	}
	/**
	 * private helper to see if there is a space in the license name to throw an exception
	 * @param space input string to see if there is a space in license
	 * @return whether or not there is one or not
	 */
	private boolean licenseSpace(String space) {
		if (space.contains("\t") || space.contains("/t")) {
			return true;
		}
		for (int i = 0; i < space.length(); i++) {
			if (space.charAt(i) == ' ') {
				return true;
			}
		}
		return false;
	}
	//make sure to check for a tab, a teaching staff test case uses a tab character
}
