import java.io.IOException;
import java.util.Scanner;


public class probInst {
	private int[][] probArray;
	private int n;
	
	public probInst(Scanner sc) throws IOException {
		String[] tokens = sc.nextLine().split(" ");
		this.n = Integer.parseInt(tokens[1]);
		
		probArray = new int[this.n][this.n];
		for (int i = 0; i < this.n; i++) {
			tokens = sc.nextLine().split(" ");
			for (int j = 0; j < this.n; j++) {
				if (tokens[j].equals(".")) {
					this.probArray[i][j] = -1; // indicates that this index is one that must be filled
				}
				else {
					this.probArray[i][j] = Integer.parseInt(tokens[j]);
				}
			}
		}
	}
	
	protected void printArray () {
		for(int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				System.out.print(probArray[i][j]+" ");
			}
			System.out.println();
		}
	}
}
