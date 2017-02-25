import backend.Model;
import backend.Parser;


public class Main {

	
	public static void main(String[] argv) {
		Model m = new Model();
		m.handleInput("sum sum sum 5 sum sum 1 2 4 6 7"
				+ " sum sum sum 5 6 0 9");
		//m.handleInput("make :abc 6");
	}
}
