import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


public class JavaMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(new File("q_10_01.qcp"));
			probInst q_10_01 = new probInst(sc);
			TreeSet<Integer> whichFormulas = new TreeSet<Integer>();
			whichFormulas.add(1);
			whichFormulas.add(2);
			whichFormulas.add(3);
			whichFormulas.add(4);
			whichFormulas.add(5);
			whichFormulas.add(6);
			
			QCPtoCNF converter = new QCPtoCNF();
			converter.constructFormulas(q_10_01, "q_10_01", whichFormulas);
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
