//******************************************************************************
// File:    TriangleRunnable.java
// Author:	Martin Suarez
//******************************************************************************

/**
 * Class containing a Runnable that calculates the value
 * of the specified number in the Pascal's Triangle.
 * @author Martin Suarez
 * @version 09-21-2015
 */
public class TriangleRunnable implements Runnable {
	// Hidden private members
	private OverallTriangle main;
	private int r, c, A, B, S;
	
	/**
	 * Constructor
	 * @param  r  Row
	 * @param  c  Column
	 * @param  A  Left offset
	 * @param  B  Right offset
	 * @param  S  Start location
	 */
	public TriangleRunnable( OverallTriangle main, 
			int r, int c, int A, int B, int S ) {
		this.main = main;
		this.r = r;
		this.c = c;
		this.A = A;
		this.B = B;
		this.S = S;
	}
	
	/**
	 * Recursive function used to calculate number value.
	 * @param  r  Row
	 * @param  c  Column
	 * @return Pascal number at specified location
	 */
	private int calc( int r, int c) {
		try {
			if (c == 1 && r == 1)
				return S;
			else if (c == 1 ) {
				return A + main.getValue(r-1, 1); 
			}
			else if (r == c) {
				return B + main.getValue(r-1, c-1); 
			}
			else {
				return main.getValue(r-1, c-1) + main.getValue(r-1, c);
			}
		} catch (InterruptedException exc) { return -1;}
	}
	
	/**
	 * Thread method.
	 */
	public void run() {
		{			
			main.putValue(r, c, calc(r, c));
		} 
	}
	
}