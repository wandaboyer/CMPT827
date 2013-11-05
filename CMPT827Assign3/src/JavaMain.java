import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;


public class JavaMain {
	private static void createEncodings (String filename, TreeSet<Integer> whichEncoding) throws IOException {
		Scanner sc = new Scanner(new File(filename+".qcp"));
		probInst prob = new probInst(sc);
		QCPtoCNF converter = new QCPtoCNF();
		converter.constructFormulas(prob, filename, whichEncoding);
	}
	
	public static void main(String[] args) {
		try{
			TreeSet<Integer> cnfEncoding2D = new TreeSet<Integer>();
			cnfEncoding2D.add(1);
			cnfEncoding2D.add(2);
			cnfEncoding2D.add(3);
			
			TreeSet<Integer> cnfEncoding3D = new TreeSet<Integer>();
			cnfEncoding3D.add(1);
			cnfEncoding3D.add(2);
			cnfEncoding3D.add(3);
			cnfEncoding3D.add(4);
			cnfEncoding3D.add(5);
			cnfEncoding3D.add(6);
			
			TreeSet<Integer> smarterClauses = new TreeSet<Integer>();
			smarterClauses.add(7);
			smarterClauses.add(8);
			smarterClauses.add(9);
			smarterClauses.add(10);
			smarterClauses.add(11);
			smarterClauses.add(12);
			
			createEncodings(args[0], cnfEncoding3D);
			System.out.println("Done!");
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
