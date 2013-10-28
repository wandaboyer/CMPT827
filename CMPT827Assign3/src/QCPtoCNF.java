import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class QCPtoCNF {
	private int numVars = 0;
	private int numClauses = 0;
	
	protected void constructFormulas (probInst problemToConvert, String name, ArrayList<Integer> whichFormulas) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(name+".cnf");
		String outputString = new String();
		outputString = createUnitClauses(problemToConvert);
		
		while (!whichFormulas.isEmpty()) {
			if (whichFormulas.contains((Integer)1)) {
				outputString += ALO1(problemToConvert);
				whichFormulas.remove((Integer)1);
			}
			if (whichFormulas.contains((Integer)2)) {
				outputString += AMO2(problemToConvert);
				whichFormulas.remove((Integer)2);
			}
			if (whichFormulas.contains((Integer)3)) {
				outputString += AMO3(problemToConvert);
				whichFormulas.remove((Integer)3);
			}
			if (whichFormulas.contains((Integer)4)) {
				outputString += AMO1(problemToConvert);
				whichFormulas.remove((Integer)4);
			}
			if (whichFormulas.contains((Integer)5)) {
				outputString += ALO2(problemToConvert);
				whichFormulas.remove((Integer)5);
			}
			if (whichFormulas.contains((Integer)6)) {
				outputString += ALO3(problemToConvert);
				whichFormulas.remove((Integer)6);
			}
		}
		outputString = "p cnf " + numVars + " " + numClauses + "\n" + outputString;
		output.write(outputString);
		output.flush(); 
		output.close();
	}
	
	private String createUnitClauses(probInst problemToConvert) {
		String outputString = new String();
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] != -1) {
					outputString += triplesToInteger(row, col, problemToConvert.getProbArr()[row][col], problemToConvert) + " 0\n";
					this.numClauses++;
					this.numVars++;
				}
			}
		}
		return outputString;
	}
	
	/*
	 * ALO-1 : "At least one colour per cell"
	 */
	private String ALO1(probInst problemToConvert) {
		String outputString = new String();
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] == -1) {
					for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
						outputString += triplesToInteger(row, col, colour, problemToConvert) + " ";
						this.numVars++;
					}
					outputString += "0\n";
					this.numClauses++;
				}
			}	
		}
		return outputString;
	}
	
	/*
	 * ALO-2 : "Each colour appears at least once in each row." CHANGEME
	 */
	private String ALO2(probInst problemToConvert) {
		String outputString = new String();
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col = 0; col < problemToConvert.getN(); col++) {
					if (problemToConvert.getProbArr()[row][col] == -1) {
						outputString += triplesToInteger(row, col, colour, problemToConvert) + " ";
						this.numVars++;
					}
				}
				outputString += "0\n";
				this.numClauses++;
			}	
		}
		return outputString;
	}
	
	
	/*
	 * ALO-3 : "Each colour appears at least once in each column." CHANGEME
	 */
	private String ALO3(probInst problemToConvert) {
		String outputString = new String();
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row = 0; row < problemToConvert.getN(); row++) {
					if (problemToConvert.getProbArr()[row][col] == -1) {
						outputString += triplesToInteger(row, col, colour, problemToConvert) + " ";
						this.numVars++;
					}
				}
				outputString += "0\n";
				this.numClauses++;
			}	
		}
		return outputString;
	}
	/*
	 * AMO-1 : "Every cell is coloured with at most 1 colour."
	 */
	private String AMO1(probInst problemToConvert) {
		String outputString = new String();
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 1; col <= problemToConvert.getN(); col++) {
				for (int col1 = 1; col1 <= problemToConvert.getN(); col1++) {
					for (int col2 = col1; col2 <= problemToConvert.getN(); col2++) {
						
					}
				}
			}
		}

		
		
		return outputString;
	}
	
	/*
	 * AMO-2 : "No colour appears more than once in the same row."
	 */
	private String AMO2(probInst problemToConvert) {
		String outputString = "";

		
		
		return outputString;
	}
	
	/*
	 * AMO-3 : "No colour appears more than once in the same column."
	 */
	private String AMO3(probInst problemToConvert) {
		String outputString = "";

		
		
		return outputString;
	}
	
	private int triplesToInteger (int row, int col, int colour, probInst problemToConvert) {
		/*
		 * Uniquely convert a boolean variable into an integer for DIMACS formatting, which is actually base (n+1)
		 */
		return (int)((row+1) + (col+1)*(problemToConvert.getN()+1) + colour*Math.pow(problemToConvert.getN()+1, 2));
	}
	
}
