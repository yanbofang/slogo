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
		Application.launch(argv);

	}
}