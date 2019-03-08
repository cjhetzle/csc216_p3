package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * the Garage class 
 * @author Cameron
 * @author Marshall Skelton
 *
 */
public class Garage {
	/** max rooms */
	private static final int MAX_ROOMS = 30;
	/** default size */
	private static final int DEFAULT_SIZE = 8;
	/** size */
	private int size;
	/** service bay array*/
	private ServiceBay[] bay;
	/**
	 * garage constructor
	 */
	public Garage() {
		ServiceBay.startBayNumberingAt101();
		initBays(DEFAULT_SIZE);
	}
	/**
	 * initial bay construction
	 * @param num number of bays for construction
	 */
	private void initBays(int num) {
		
		//may need to ReWrite, just for testing the GUI
		
		bay = new ServiceBay[30];
		
		while (size < num) {
			addRepairBay();
		}
		
	}
	/**
	 * add repair bay
	 */
	public void addRepairBay() {
		if (size + 1 > MAX_ROOMS) {
			//throw something
			return;
		}
		
		ServiceBay newBay = null;
		
		if ((size + 3) % 3 == 0) {
			//if the next bay is to be HEV then we add it
			//to the end of the list at size
			newBay = new HybridElectricBay();
			bay[size] = newBay;
		} else {
			//if the next bay is regular
			//we shift the list down one and add it at index 0
			newBay = new ServiceBay();
			for(int i = 29; i > 0; i--) {
				bay[i] = bay[i - 1];
			}
			bay[0] = newBay;
		}
		
		//increment the size by one
		size++;
	}
	/**
	 * returns number of empty service bays
	 * @return numerical amount of empty bays
	 */
	public int numberOfEmptyBays() {
		int empty = 0;
		
		for (int i = 0; i < size; i++) {
			if (!bay[i].isOccupied())
				empty++;
		}
		return empty;
	}
	/**
	 * retrieves a service bay
	 * @param index the index
	 * @return the service bay requested by the call
	 */
	public ServiceBay getBayAt(int index) {
		return bay[index];
	}
	/**
	 * gets the size of the garage
	 * @return int of the size 
	 */
	public int getSize() {
		return size;
	}
	/**
	 * release vehicle from the garage
	 * @param index the index
	 * @return the vehicle to be released from the garage
	 */
	public Vehicle release(int index) {
		if (bay[index] == null) {
			return null;
		}
		return bay[index].release();
	}
}