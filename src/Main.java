import backend.Model;
import frontend.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	public void start(Stage arg0) throws Exception {
		Model model = new Model();
		View view = new View(arg0);
		view.runView(model);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
