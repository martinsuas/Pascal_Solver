//******************************************************************************
// File:    GenPasTri.java
// Author:	Martin Suarez
//******************************************************************************

/**
 * Class GenPasTri is the main class of the program. It has 
 * the sole purpose of declaring, initializing, and calling the 
 * other objects and check the input for any errors.
 * @author Martin Suarez
 * @version 09-21-2015
 */
public class GenPasTri {
	
	// Prevent construction
	private GenPasTri() {}
	
	// Main program
	public static void main (String args[]) throws Throwable {
		if (args.length != 4) use();
		try {
			int[] num =  new int[4];
			num[0] = Integer.parseInt(args[0]); // N
			num[1] = Integer.parseInt(args[1]); // A
			num[2] = Integer.parseInt(args[2]); // B
			num[3] = Integer.parseInt(args[3]); // S
			
			// Check for negatives
			for (int n : num ) {
				if (n < 0 ) {
					use();
				}
			}
			
			// Try to create new Pascal Triangle
			OverallTriangle main = new OverallTriangle(num[0]);
			
			// Initialize threads
			new Thread( new PrinterRunnable(main) ).start();
			for (int i = num[0]; i >= 1; i-- ) {
				for (int j = i; j >= 1; j--) {
					new Thread( new TriangleRunnable(main, i, j, 
						num[1], num[2], num[3]) ).start();
				}
			}
		// Error Handling for invalid number format
		} catch (NumberFormatException nfe) {
			System.err.println("NumberFormatException: " + nfe.getMessage());
			System.exit (1);
		}
	}
	
	// Use information
	private static void use() {
		System.err.println("Usage: GenPasTri <N> <A> <B> <S>");
		System.err.println("All arguments must be >= 0.");
		System.exit (1);
	}
}

