import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class QCPtoCNF {
	private int numClauses = 0;
	private TreeSet<Integer> vars;
	
	protected void constructFormulas (probInst problemToConvert, String name, TreeSet<Integer> whichFormulas) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(name+"INTERMED.cnf");
		//String outputString = new String();
		vars = new TreeSet<Integer>();
		
		//outputString = createUnitClauses(problemToConvert);
		createUnitClauses(problemToConvert, output);
		while (!whichFormulas.isEmpty()) {
			if (whichFormulas.contains(1)) {
				// outputString += ALO1(problemToConvert);
				ALO1(problemToConvert, output);
				whichFormulas.remove(1);
			}
			if (whichFormulas.contains(2)) {
				//outputString += AMO2(problemToConvert);
				AMO2(problemToConvert, output);
				whichFormulas.remove(2);
			}
			if (whichFormulas.contains(3)) {
				//outputString += AMO3(problemToConvert);
				AMO3(problemToConvert, output);
				whichFormulas.remove(3);
			}
			if (whichFormulas.contains(4)) {
				//outputString += AMO1(problemToConvert);
				AMO1(problemToConvert, output);
				whichFormulas.remove(4);
			}
			if (whichFormulas.contains(5)) {
				//outputString += ALO2(problemToConvert);
				ALO2(problemToConvert, output);
				whichFormulas.remove(5);
			}
			if (whichFormulas.contains(6)) {
				//outputString += ALO3(problemToConvert);
				ALO3(problemToConvert, output);
				whichFormulas.remove(6);
			}
		}
		//outputString = "p cnf " + vars.size() + " " + numClauses + "\n" + outputString;
		//output.write(outputString);
		//output.write();
		output.flush(); 
		output.close();
		
		PrintWriter out = new PrintWriter(name+".cnf");
	    out.write("p cnf " + vars.size() + " " + numClauses + "\n");
	    Scanner sc = new Scanner (new File(name+"INTERMED.cnf"));
	    while (sc.hasNext()) {
	    	out.write(sc.nextLine()+"\n");
	    }
	    sc.close();
	    out.flush();
	    out.close();
	}
	
	private void createUnitClauses(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] != -1) {
					dummy = triplesToInteger(row, col, problemToConvert.getProbArr()[row][col], problemToConvert);
					//outputString += dummy + " 0\n";
					output.write(dummy + " 0\n");
					this.numClauses++;
					this.vars.add(dummy);
				}
			}
		}
		//return outputString;
	}
	
	/*
	 * ALO-1 : "At least one colour per cell"
	 */
	private void ALO1(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				if (problemToConvert.getProbArr()[row][col] == -1) {
					for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
						dummy = triplesToInteger(row, col, colour, problemToConvert);
						//outputString += dummy + " ";
						output.write(dummy + " ");
						vars.add(dummy);
					}
					//outputString += "0\n";
					output.write("0\n");
					this.numClauses++;
				}
			}	
		}
		//return outputString;
	}
	
	/*
	 * ALO-2 : "Each colour appears at least once in each row." CHANGEME
	 */
	private void ALO2(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col = 0; col < problemToConvert.getN(); col++) {
					if (problemToConvert.getProbArr()[row][col] == -1) {
						dummy = triplesToInteger(row, col, colour, problemToConvert);
						//outputString += dummy + " ";
						output.write(dummy + " ");
						vars.add(dummy);
					}
				}
				//outputString += "0\n";
				output.write("0\n");
				this.numClauses++;
			}	
		}
		//return outputString;
	}
	
	
	/*
	 * ALO-3 : "Each colour appears at least once in each column." CHANGEME
	 */
	private void ALO3(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy;
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row = 0; row < problemToConvert.getN(); row++) {
					if (problemToConvert.getProbArr()[row][col] == -1) {
						dummy = triplesToInteger(row, col, colour, problemToConvert);
						//outputString += dummy + " ";
						output.write(dummy + " ");
						vars.add(dummy);
					}
				}
				//outputString += "0\n";
				output.write("0\n");
				this.numClauses++;
			}	
		}
		//return outputString;
	}
	/*
	 * AMO-1 : "Every cell is coloured with at most 1 colour."
	 */
	private void AMO1(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy1, dummy2;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int col = 0; col < problemToConvert.getN(); col++) {
				for (int colour1 = 1; colour1 <= problemToConvert.getN(); colour1++) {
					for (int colour2 = colour1 + 1; colour2 <= problemToConvert.getN(); colour2++) {
						//if (colour1 != colour2) {
							dummy1 = triplesToInteger(row, col, colour1, problemToConvert);
							dummy2 = triplesToInteger(row, col, colour2, problemToConvert);
							//outputString += -dummy1 + " " + -dummy2 + " 0\n";
							output.write(-dummy1 + " " + -dummy2 + " 0\n");
							this.numClauses++;
							this.vars.add(dummy1);
							this.vars.add(dummy2);
						//}	
					}
				}
			}
		}
		//return outputString;
	}
	
	/*
	 * AMO-2 : "No colour appears more than once in the same row."
	 */
	private void AMO2(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy1, dummy2;
		for (int row = 0; row < problemToConvert.getN(); row++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int col1 = 0; col1 < problemToConvert.getN(); col1++) {
					for (int col2 = col1 + 1; col2 < problemToConvert.getN(); col2++) {
						//if (col1 != col2) {
							dummy1 = triplesToInteger(row, col1, colour, problemToConvert);
							dummy2 = triplesToInteger(row, col2, colour, problemToConvert);
							//outputString += -dummy1 + " " + -dummy2 + " 0\n";
							output.write(-dummy1 + " " + -dummy2 + " 0\n");
							this.numClauses++;
							this.vars.add(dummy1);
							this.vars.add(dummy2);
						//}
					}
				}
			}
		}
		//return outputString;
	}
	
	/*
	 * AMO-3 : "No colour appears more than once in the same column."
	 */
	private void AMO3(probInst problemToConvert, PrintWriter output) {
		//String outputString = new String();
		int dummy1, dummy2;
		for (int col = 0; col < problemToConvert.getN(); col++) {
			for (int colour = 1; colour <= problemToConvert.getN(); colour++) {
				for (int row1 = 0; row1 < problemToConvert.getN(); row1++) {
					for (int row2 = row1 + 1; row2 < problemToConvert.getN(); row2++) {
						//if (row1 != row2) {
							dummy1 = triplesToInteger(row1, col, colour, problemToConvert);
							dummy2 = triplesToInteger(row2, col, colour, problemToConvert);
							//outputString += -dummy1 + " " + -dummy2 + " 0\n";
							output.write(-dummy1 + " " + -dummy2 + " 0\n");
							this.numClauses++;
							this.vars.add(dummy1);
							this.vars.add(dummy2);
						//}
					}
				}
			}
		}
		//return outputString;
	}
	
	private int triplesToInteger (int row, int col, int colour, probInst problemToConvert) {
		/*
		 * Uniquely convert a boolean variable into an integer for DIMACS formatting, which is actually base (n+1)
		 */
		return (int)((row+1) + (col+1)*(problemToConvert.getN()) + colour*Math.pow(problemToConvert.getN(), 2));
	}
	
}
