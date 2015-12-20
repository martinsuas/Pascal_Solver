//******************************************************************************
// File:    PrinterRunnable.java
// Author:	Martin Suarez
//******************************************************************************

/**
 * Runnable object used to print the output as soon as it becomes
 * available from the TriangleRunnable threads.
 * @author Martin Suarez
 * @version 09-21-2015
 */
public class PrinterRunnable implements Runnable {
	// Hidden private members
	private OverallTriangle main;
	
	/**
	 * Constructor
	 * @param  main  Main object containing Pascal's Triangle
	 */
	public PrinterRunnable( OverallTriangle main ) {
		this.main = main;
	}
	
	/**
	 * Thread method.
	 */
	public void run() {
		try {
			int N = main.rows();
			for (int i = 1; i <= N; i++ ) {
				for (int j = 1; j <= i; j++) {
					System.out.print( main.getValue(i, j) );
					if ( j != i )
						System.out.print( " " );
				}
				System.out.print("\n");
			}
		} catch (InterruptedException exc) {} // Shouldn't happen.
	}
}