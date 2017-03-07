import backend.Model;
import controller.Controller;
import frontend.View;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{

	public void start(Stage arg0) throws Exception {
		Controller controller = new Controller(arg0);
//		controller.start();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
