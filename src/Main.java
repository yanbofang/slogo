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

//	public static void main(String[] argv) {
//		m.handleInput("sum sum sum 5 sum sum 1 2 4 6 7"
//				+ " sum sum sum 5 6 0 9");
//		//m.handleInput("setxy 5 0");
//	m.handleInput("REPEAT 3 [ sum sum sum 5 sum sum 1 2 4 6 7 ]");
//		m.getNextPos();
//	}
}