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
				outputString += ALO1();
				whichFormulas.remove((Integer)1);
			}
			if (whichFormulas.contains((Integer)2)) {
				outputString += AMO2();
				whichFormulas.remove((Integer)2);
			}
			if (whichFormulas.contains((Integer)3)) {
				outputString += AMO3();
				whichFormulas.remove((Integer)3);
			}
			if (whichFormulas.contains((Integer)4)) {
				outputString += AMO1();
				whichFormulas.remove((Integer)4);
			}
			if (whichFormulas.contains((Integer)5)) {
				outputString += ALO2();
				whichFormulas.remove((Integer)5);
			}
			if (whichFormulas.contains((Integer)6)) {
				outputString += ALO3();
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
					outputString += triplesToInteger(row, col, problemToConvert) + " 0";
					if (!(row == problemToConvert.getN() - 1 && col == problemToConvert.getN())) {
						outputString+="\n";
					}
					this.numClauses++;
				}
			}
		}
		return outputString;
	}
	
	/*
	 * ALO-1 : "At least one colour per cell"
	 */
	private String ALO1() {
		String outputString = "";
		
		
		
		return outputString;
	}
	
	/*
	 * ALO-2 : "Each colour appears at least once in each row."
	 */
	private String ALO2() {
		String outputString = "";

		
		
		return outputString;
	}
	
	
	/*
	 * ALO-3 : "Each colour appears at least once in each column."
	 */
	private String ALO3() {
		String outputString = "";

		
		
		return outputString;
	}
	/*
	 * AMO-1 : "Every cell is coloured with at most 1 colour."
	 */
	private String AMO1() {
		String outputString = "";

		
		
		return outputString;
	}
	
	/*
	 * AMO-2 : "No colour appears more than once in the same row."
	 */
	private String AMO2() {
		String outputString = "";

		
		
		return outputString;
	}
	
	/*
	 * AMO-3 : "No colour appears more than once in the same column."
	 */
	private String AMO3() {
		String outputString = "";

		
		
		return outputString;
	}
	
	private int triplesToInteger (int row, int col, probInst problemToConvert) {
		/*
		 * Uniquely convert a boolean variable into an integer for DIMACS formatting, which is actually base (n+1)
		 */
		return (int)((row+1) + (col+1)*(problemToConvert.getN()+1) + problemToConvert.getProbArr()[row][col]*Math.pow(problemToConvert.getN()+1, 2));
	}
	
}
