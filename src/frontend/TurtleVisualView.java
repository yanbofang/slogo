package frontend;

import java.util.List;
import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import turtles.Turtle;
import turtles.TurtleAPI;
import turtles.TurtleManager;

public class TurtleVisualView implements SubcomponentAPI {

	View view;
	TurtleView tView;
	ResourceBundle resource;
	HBox turtleView;
	
	public TurtleVisualView(View viewIn){
		view = viewIn;
		tView = new TurtleView(view);
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		turtleView = new HBox();
		toggleTurtle();
	}  
	
	private void toggleTurtle() {
		TurtleManager tManager = new TurtleManager(tView.getBounds());
		List<TurtleAPI> activeTurtles = tManager.getActiveTurtles();
		Label title = new Label("Toggle Visibility of Active Turtle");
		turtleView.getChildren().add(title);
		for(TurtleAPI turtle: activeTurtles) {
			Label activeTurtle = new Label(turtle.toString());
			turtleView.getChildren().add(activeTurtle);
			activeTurtle.setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent t) {
		            if (tView.getParent().getChildrenUnmodifiable().contains(turtle)) {
		            	tView.removeTurtle((Node) turtle);
		            }
		            else {
		            	tView.placeTurtle((Node) turtle);
		            }
		        }
		    });
		}
	}
	
	
	@Override
	public Parent getParent() {
		return turtleView;
	}

}
