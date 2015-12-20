//******************************************************************************
// File:    OverallTriangle.java
// Author:	Martin Suarez
//******************************************************************************

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * This class contains the object representation of our Pascal Triangle
 * as well as the synchronized functions to control it.
 * @author Martin Suarez
 * @version 09-21-2015
 */
public class OverallTriangle {
	// Hidden private members
	private int N; 
	private ArrayList<LinkedList<Integer>> rows;
	
	/**
	 * Constructor:
	 * @param  N  Number of rows
	 */
	public OverallTriangle(int N) {
		this.N = N;
		makeRows();
	}
	
	/**
	 * Private function to initialize the Pascal Triangle.
	 */
	private synchronized void makeRows() {
		rows =	new ArrayList<LinkedList<Integer>>(N);
		
		for (int i = 0; i < N; i++){
			rows.add(i, new LinkedList());
			for (int j = 0; j <= i; j++){
				rows.get(i).add(null);
			}
		}
	}
		
	/**
	 * @return Number of rows
	 */
	public synchronized int rows() {
		return N;
	}
	
	/**
	 * Puts a value in specified location.
	 * @param  r  Row
	 * @param  c  Column
	 * @param  value  Value to be inserted
	 */
	public synchronized void putValue( int r, int c, int value) {
		if (rows.get(r-1).get(c-1) == null) {
			rows.get(r-1).set(c-1, value);
		} else {
			System.err.println("Location already occupied.");
			System.exit (1);
		}
		notifyAll();
	}
	
	/**
	 * Gets a value from the specified location.
	 * @param  r  Row
	 * @param  c  Column
	 * @exception  InterruptedException  
	 * @return value from specified location
	 */
	public synchronized int getValue( int r, int c ) 
		throws InterruptedException {
		while( rows.get(r-1).get(c-1) == null ) {
			wait();
		}
		notifyAll();
		return rows.get(r-1).get(c-1);
	}
}