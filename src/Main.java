import backend.Model;
import frontend.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	
	private static final String[] SYNTAX = new String[]{"resources/languages/English", "resources/languages/Syntax"};
	private static Model m = new Model(SYNTAX, null);

	public void start(Stage arg0) throws Exception {
		View view = new View(arg0);
		view.runView(m);
	}
	
	public static void main(String[] argv) {
		String[] syntax = new String[]{"resources/languages/English", "resources/languages/Syntax"};
		Model m = new Model(syntax, null);
//		m.handleInput("sum sum sum 5 sum sum 1 2 4 6 7"
//				+ " sum sum sum 5 6 0 9");
		m.handleInput("setxy 5 0");
		m.handleInput("setheading 90");
		m.handleInput("towards 0 0");

		m.getNextPos();

	}
}