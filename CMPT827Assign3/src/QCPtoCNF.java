import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class QCPtoCNF {
	private int numVars = 0;
	private int numClauses = 0;
	
	public QCPtoCNF(probInst problemToConvert, String name, ArrayList<Integer> whichFormulas) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(name+".cnf");
		String outputString = createUnitClauses(problemToConvert);
		
		for (int i = 0; i < whichFormulas.size(); i++) {
			if (whichFormulas.contains(1)) {
				outputString.concat(ALO1());
			}
			if (whichFormulas.contains(2)) {
				outputString.concat(AMO2());
			}
			if (whichFormulas.contains(3)) {
				outputString.concat(AMO3());
			}
			if (whichFormulas.contains(4)) {
				outputString.concat(AMO1());
			}
			if (whichFormulas.contains(5)) {
				outputString.concat(ALO2());
			}
			if (whichFormulas.contains(6)) {
				outputString.concat(ALO3());
			}
		}
		problemToConvert.printArray();
		outputString = "p cnf " + numVars + " " + numClauses + "\n" + outputString;
		System.out.println(outputString);
		output.write(outputString);
		output.flush(); 
		output.close();
	}
	
	protected String createUnitClauses(probInst problemToConvert) {
		String outputString = "";
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] != -1) {
					outputString.concat(triplesToInteger(row, col, problemToConvert)+" 0");
					if (!(row == problemToConvert.getN() - 1 && col == problemToConvert.getN())) {
						outputString.concat("\n");
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
	protected String ALO1() {
		String outputString = "";
		
		
		
		return outputString;
	}
	
	/*
	 * ALO-2 : "Each colour appears at least once in each row."
	 */
	protected String ALO2() {
		String outputString = "";

		
		
		return outputString;
	}
	
	
	/*
	 * ALO-3 : "Each colour appears at least once in each column."
	 */
	protected String ALO3() {
		String outputString = "";

		
		
		return outputString;
	}
	/*
	 * AMO-1 : "Every cell is coloured with at most 1 colour."
	 */
	protected String AMO1() {
		String outputString = "";

		
		
		return outputString;
	}
	
	/*
	 * AMO-2 : "No colour appears more than once in the same row."
	 */
	protected String AMO2() {
		String outputString = "";

		
		
		return outputString;
	}
	
	/*
	 * AMO-3 : "No colour appears more than once in the same column."
	 */
	protected String AMO3() {
		String outputString = "";

		
		
		return outputString;
	}
	
	private int triplesToInteger (int row, int col, probInst problemToConvert) {
		/*
		 * Uniquely convert a boolean variable into an integer for DIMACS formatting, which is actually base (n+1)
		 */
		return (int) ((row+1) + (col+1)*(problemToConvert.getN()+1) + problemToConvert.getProbArr()[row][col]*Math.pow(problemToConvert.getN()+1, 2));
	}
	
}
