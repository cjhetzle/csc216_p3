package edu.ncsu.csc216.garage.model.util;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * the Simple Iterator interface
 * @author Marshall Skelton
 *
 * @param <E> element
 */
public interface SimpleIterator<E> {
	/**
	 * has next method for iterating
	 * @return true if has next
	 */
	public boolean hasNext();
	/**
	 * next method calls the next object
	 * @return the object 
	 */
	public Vehicle next();
}
