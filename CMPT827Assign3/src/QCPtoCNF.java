import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class QCPtoCNF {
	private int numClauses = 0;
	private int largestVar = 0;

	protected void constructFormulas (probInst problemToConvert, String name, TreeSet<Integer> whichFormulas) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(name+".intermediate");
		
		createUnitClauses(problemToConvert, output);
		while (!whichFormulas.isEmpty()) {
			if (whichFormulas.contains(1)) {
				ALO1(problemToConvert, output);
				whichFormulas.remove(1);
			}
			if (whichFormulas.contains(2)) {
				AMO2(problemToConvert, output);
				whichFormulas.remove(2);
			}
			if (whichFormulas.contains(3)) {
				AMO3(problemToConvert, output);
				whichFormulas.remove(3);
			}
			if (whichFormulas.contains(4)) {
				AMO1(problemToConvert, output);
				whichFormulas.remove(4);
			}
			if (whichFormulas.contains(5)) {
				ALO2(problemToConvert, output);
				whichFormulas.remove(5);
			}
			if (whichFormulas.contains(6)) {
				ALO3(problemToConvert, output);
				whichFormulas.remove(6);
			}
			if (whichFormulas.contains(7)) {
				ALO1Opt(problemToConvert, output);
				whichFormulas.remove(7);
			}
			if (whichFormulas.contains(8)) {
				AMO2Opt(problemToConvert, output);
				whichFormulas.remove(8);
			}
			if (whichFormulas.contains(9)) {
				AMO3Opt(problemToConvert, output);
				whichFormulas.remove(9);
			}
			if (whichFormulas.contains(10)) {
				AMO1Opt(problemToConvert, output);
				whichFormulas.remove(10);
			}
			if (whichFormulas.contains(11)) {
				ALO2Opt(problemToConvert, output);
				whichFormulas.remove(11);
			}
			if (whichFormulas.contains(12)) {
				ALO3Opt(problemToConvert, output);
				whichFormulas.remove(12);
			}
		}
		output.flush(); 
		output.close();
		
		PrintWriter out = new PrintWriter(name+".cnf");
	    	out.write("p cnf " + this.largestVar + " " + this.numClauses + "\n");
	    Scanner sc = new Scanner (new File(name+".intermediate"));
	    while (sc.hasNext()) {
	    	out.write(sc.nextLine()+"\n");
	    }
	    sc.close();
	    out.flush();
	    out.close();
	}
	
	private void createUnitClauses(probInst problemToConvert, PrintWriter output) {
		int dummy = 0;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] != -1) {
					output.write(triplesToInteger(row, col, problemToConvert.getProbArr()[row][col], problemToConvert) + " 0\n");
					this.numClauses++;
				}
			}
		}
	}
	
	/*
	 * ALO-1 : "At least one colour per cell"
	 */
	private void ALO1(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
					output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
				}
				output.write("0\n");
				this.numClauses++;
			}	
		}
	}
	
	/*
	 * ALO-2 : "Each colour appears at least once in each row."
	 */
	private void ALO2(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col = 0; col < problemToConvert.getN(); col++) {
					output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
				}
				output.write("0\n");
				this.numClauses++;
			}	
		}
	}
	
	
	/*
	 * ALO-3 : "Each colour appears at least once in each column."
	 */
	private void ALO3(probInst problemToConvert, PrintWriter output) {
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row = 0; row < problemToConvert.getN(); row++) {
					output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
				}
				output.write("0\n");
				this.numClauses++;
			}	
		}
	}

	/*
	 * AMO-1 : "Every cell is coloured with at most 1 colour."
	 */
	private void AMO1(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				for (int colour1 = 1; colour1 <= problemToConvert.getN(); colour1++) {
					for (int colour2 = 1; colour2 <= problemToConvert.getN(); colour2++) {
						if (colour1 != colour2) {
							output.write(-triplesToInteger(row, col, colour1, problemToConvert) + " " + -triplesToInteger(row, col, colour2, problemToConvert) + " 0\n");
						}
						this.numClauses++;
					}
				}
			}
		}
	}
	
	/*
	 * AMO-2 : "No colour appears more than once in the same row."
	 */
	private void AMO2(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col1 = 0; col1 < problemToConvert.getN(); col1++) {
					for (int col2 = 1; col2 < problemToConvert.getN(); col2++) {
						if (col1 != col2) {
							output.write(-triplesToInteger(row, col1, colour, problemToConvert)+ " " + -triplesToInteger(row, col2, colour, problemToConvert) + " 0\n");
						}
						this.numClauses++;
					}
				}
			}
		}
	}
	
	/*
	 * AMO-3 : "No colour appears more than once in the same column."
	 */
	private void AMO3(probInst problemToConvert, PrintWriter output) {
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row1 = 0; row1 < problemToConvert.getN(); row1++) {
					for (int row2 = 1; row2 < problemToConvert.getN(); row2++) {
						if (row1 != row2) {
							output.write(-triplesToInteger(row1, col, colour, problemToConvert) + " " + -triplesToInteger(row2, col, colour, problemToConvert) + " 0\n");
						}
						this.numClauses++;
					}
				}
			}
		}
	}

/*															*/
/************************************************************************************************************************/
/*															*/


	/*
	 * ALO-1  (Optimized - ): "At least one colour per cell"
	 */
	private void ALO1Opt(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] == -1) {
					for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
						output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
					}
					output.write("0\n");
					this.numClauses++;
				}
			}	
		}
	}
	

	/*
	 * ALO-2 (Optimized - if a colour already appears in a row, then we don't need clauses to ensure that happens):
	 *		 "Each colour appears at least once in each row."
	 */
	private void ALO2Opt(probInst problemToConvert, PrintWriter output) {
		TreeSet<Integer> acceptableColours = new TreeSet<Integer>();
		for (int i = 1; i <= problemToConvert.getN(); i++) {
			acceptableColours.add(i);
		}
		int[][] dummy = problemToConvert.getProbArr();
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int j = 0; j < problemToConvert.getN(); j++) {
				acceptableColours.remove(dummy[row][j]);
			}
			for (int colour: acceptableColours) {
				for (int col = 0; col < problemToConvert.getN(); col++) {
					// if the colour appears in the row already, don't make a clause for it
					output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
				}
				output.write("0\n");
				this.numClauses++;
			}	
		}
	}
	
	
	/*
	 * ALO-3 (Optimized - if a colour already appears in a column, then we don't need clauses to ensure that happens): 
	 *		"Each colour appears at least once in each column."
	 */
	private void ALO3Opt(probInst problemToConvert, PrintWriter output) {
		TreeSet<Integer> acceptableColours = new TreeSet<Integer>();
		for (int i = 1; i <= problemToConvert.getN(); i++) {
			acceptableColours.add(i);
		}

		int[][] dummy = problemToConvert.getProbArr();
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int j = 0; j < problemToConvert.getN(); j++) {
				acceptableColours.remove(dummy[j][col]);
			}
			for (int colour: acceptableColours) {
				for (int row = 0; row < problemToConvert.getN(); row++) {
					// if the colour appears in the column already, don't make a clause for it
						output.write(triplesToInteger(row, col, colour, problemToConvert) + " ");
				}
				output.write("0\n");
				this.numClauses++;
			}	
		}
	}

	/*
	 * AMO-1  (Optimized - "smarter" innermost loop index): "Every cell is coloured with at most 1 colour."
	 */
	private void AMO1Opt(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				for (int colour1 = 1; colour1 < problemToConvert.getN(); colour1++) {
					for (int colour2 = colour1 + 1; colour2 <= problemToConvert.getN(); colour2++) {
						output.write(-triplesToInteger(row, col, colour1, problemToConvert) + " " + -triplesToInteger(row, col, colour2, problemToConvert) + " 0\n");
						this.numClauses++;
					}
				}
			}
		}
	}
	
	/*
	 * AMO-2 (Optimized - "smarter" innermost loop index): "No colour appears more than once in the same row."
	 */
	private void AMO2Opt(probInst problemToConvert, PrintWriter output) {
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col1 = 0; col1 < problemToConvert.getN()-1; col1++) {
					for (int col2 = col1 + 1; col2 < problemToConvert.getN(); col2++) {
						output.write(-triplesToInteger(row, col1, colour, problemToConvert)+ " " + -triplesToInteger(row, col2, colour, problemToConvert) + " 0\n");
						this.numClauses++;
					}
				}
			}
		}
	}
	
	/*
	 * AMO-3 (Optimized - "smarter" innermost loop index): "No colour appears more than once in the same column."
	 */
	private void AMO3Opt(probInst problemToConvert, PrintWriter output) {
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row1 = 0; row1 < problemToConvert.getN()-1; row1++) {
					for (int row2 = row1 + 1; row2 < problemToConvert.getN(); row2++) {
						output.write(-triplesToInteger(row1, col, colour, problemToConvert) + " " + -triplesToInteger(row2, col, colour, problemToConvert) + " 0\n");
						this.numClauses++;
					}
				}
			}
		}
	}

	/*
	 * Uniquely convert a boolean variable into an integer for DIMACS formatting
	 */
	private int triplesToInteger (int row, int col, int colour, probInst problemToConvert) {
		int convertVar = (int)((row) + (col)*(problemToConvert.getN()) + colour*Math.pow(problemToConvert.getN(), 2));
		if (convertVar > this.largestVar) {
			this.largestVar = convertVar;
		}
		return convertVar;
	}
}
