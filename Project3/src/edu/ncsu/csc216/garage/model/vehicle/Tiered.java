package edu.ncsu.csc216.garage.model.vehicle;
/**
 * Tiered interface
 * @author Marshall Skelton
 *
 */
public interface Tiered {
	
	/**
	 * Returns the value of tierIndex
	 * @return int tier index
	 */
	public int getTier();
	
	/**
	 * compare method
	 * @param v tier for input
	 * @return -1, 0 or 1 depending on where each tier is compare to the other
	 */
	public int compareToTier(Tiered v);
}
