package edu.ncsu.csc216.garage.model.vehicle;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * VehicleList Class has Node and Cursor as inner classes
 * @author Cameron
 * @author Marshall Skelton
 *
 */
public class VehicleList {
	/** head node */
	private Node head;
	/**
	 * vehicle list constructor
	 * @param input the scanner
	 */
	public VehicleList(Scanner input) {
		head = null;
		String t, b, c;
		int x;
		Vehicle v = null;
		
		while (input.hasNext()) {
			try {
			t = input.next();
			x = input.nextInt();
			b = input.next();
			c = input.nextLine();
			} catch (InputMismatchException e) {
				c = input.nextLine();
				continue;
			}
			switch (t.toLowerCase()) {
				case "r": try {	v = new RegularCar(b, c, x); } catch (BadVehicleInformationException e) { continue; } break;
				case "e": try {	v = new HybridElectricCar(b, c, x);	} catch (BadVehicleInformationException e) { continue; } break;
				default: continue;
			}
			this.add(v);
		}
	}
	
	/**
	 * default constructor
	 */
	public VehicleList() {
		head = null;
	}
	
	/**
	 * simple iterator method
	 * @return the simple iterator
	 */
	public SimpleIterator<Vehicle> iterator() {
		SimpleIterator<Vehicle> iterator = new Cursor<Vehicle>();
		return iterator;
	}
	
	/**
	 * remove method
	 * @param filter the string filter
	 * @param index the index
	 * @return the Vehicle being removed from the list
	 */
	public Vehicle remove(String filter, int index) {
		Node current = head;
		int filteredIndex = -1;
		if (current == null || current.v == null) {
			throw new NoSuchElementException();
		}
		if (current.v.meetsFilter(filter)) {
			filteredIndex++;
			if (index == 0) {
				Vehicle v = current.v;
				head = current.next;
				return v;
			}
		}
		
		 while (current.next != null) {
			 if (current.next.v.meetsFilter(filter)) {
				 filteredIndex++;
			 }
			 if (filteredIndex == index) {
				 Vehicle v = current.next.v;
				 current.next = current.next.next;
				 return v;
			 } else {
				 current = current.next;
			 }
		 }
		
		return null;
	}
	
	/**
	 * get method 
	 * @param filter string
	 * @param index the index
	 * @return the Vehicle requested
	 */
	public Vehicle get(String filter, int index) {
		SimpleIterator<Vehicle> iterator = iterator();
		int i = -1;
		
		while (iterator.hasNext()) {
			Vehicle v = iterator.next();
			if (v.meetsFilter(filter)) {
				i++;
				if (i == index) {
					return v;
				}
			}
		}
		return null;
	}
	
	/**
	 * add a vehicle to the list of waiting vehicles in the proper place
	 * before the first vehicle of the next lower tier
	 * @param v vehcile for adding
	 */
	public void add(Vehicle v) {
		int tier = v.getTier();
		Node newNode = new Node(v, null);
		Node current = head;
		
		//if this is a new list
		if (head == null || head.v == null) {
			head = newNode;
			return;
		}
		
		//if the head is a lower tier than the new car
		if (head.v.getTier() < tier) {
			newNode.next = head;
			head = newNode;
			return;
		}
		
		//goes through the list and adds it to the bottom of its own list
		//unless this vehicles tier is -1
		while (current.next != null) {
			if (current.next.v.getTier() < tier) {
				newNode.next = current.next;
				current.next = newNode;
				return;
			}
			current = current.next;
		}
		
		findTrailingNode("", 0).next = newNode;
	}
	
	/**
	 * find trailing node
	 * @param filter the string
	 * @param index the index
	 * @return the node
	 */
	private Node findTrailingNode(String filter, int index) {
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}
	/**
	 * Return filtered version of wait list
	 * @param filter custom string to filter by
	 * @return String of wait list
	 */
	public String filteredList(String filter) {
		Node current = head;
		String data = "";
		while (current != null) {
			if (current.v.meetsFilter(filter)) {
				data += current.v.toString() + "\n";
			}
			current = current.next;
		}
		return data;
	}
	/**
	 * Node Class
	 * @author Cameron
	 * @author Marshall Skelton
	 *
	 */
	public class Node {
		/** data for the node */
		public Vehicle v;
		/** node */
		public Node next;
		/**
		 * node constructor
		 * @param v vehicle object for the data
		 * @param n node object for next pointer
		 */
		public Node(Vehicle v, Node n) {
			this.v = v;
			this.next = n;
		}
	}
	/**
	 * Cursor Class
	 * @author Cameron
	 * @author Marshall Skelton
	 * @param <E> element
	 *
	 */
	public class Cursor<E> implements SimpleIterator<E> {
		/** node object for cursor */
		private Node cursor = head;
		
		/**
		 * default cursor constructor
		 */
		private Cursor() {
			cursor = head;
		}
		/**
		 * has next method 
		 * @return if there is the next element for not
		 */
		@Override
		public boolean hasNext() {
			return cursor != null;
		}
		/**
		 * next method 
		 * @return the next object for the cursor
		 */
		public Vehicle next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node n = cursor;
			cursor = n.next;
			return n.v;
		}
	}
}
