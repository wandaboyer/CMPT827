import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class JavaMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(new File("q_10_01.qcp"));
			probInst q_10_01 = new probInst(sc);
			q_10_01.printArray();
		}
		catch (IOException e) {
			
		}
	}

}
