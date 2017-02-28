import backend.Model;
import frontend.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	
	private static final String[] SYNTAX = new String[]{"resources/languages/English", "resources/languages/Syntax"};
	private static Model m = new Model(SYNTAX, null);

	public void start(Stage arg0) throws Exception {
		View view = new View(arg0);
		m = new Model(SYNTAX, view);
		view.runView(m);
	}
	
	public static void main(String[] argv) {
		
		Model m = new Model(SYNTAX, null);
		m.handleInput("make :f 5");
		//m.handleInput("sum sum sum 5 sum sum 1 2 4 6 7"
		//		+ " sum sum sum 5 6 0 9");
		//m.handleInput("product 50 4");
		//m.handleInput("setxy 5 0");
		//m.handleInput("setheading 90");
		//m.handleInput("towards 0 0");

		m.getNextPos();
		
		//Application.launch(argv);

	}
}