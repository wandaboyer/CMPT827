import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class JavaMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(new File("q_10_01.qcp"));
			probInst q_10_01 = new probInst(sc);
			ArrayList<Integer> whichFormulas = new ArrayList<Integer>();
			whichFormulas.add((Integer)1);
			whichFormulas.add((Integer)2);
			whichFormulas.add((Integer)3);
			whichFormulas.add((Integer)4);
			whichFormulas.add((Integer)5);
			whichFormulas.add((Integer)6);
			
			QCPtoCNF converter = new QCPtoCNF(q_10_01, "q_10_01", whichFormulas);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println("General Exception: ");
			e.printStackTrace();
		}
	}

}
