package frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import frontend.API.SubcomponentAPI;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import turtles.Turtle;
import turtles.TurtleAPI;
import turtles.TurtleManager;

public class TurtleVisualView implements SubcomponentAPI {

	private View view;
	private TurtleView tView;
	private ResourceBundle resource;
	private VBox turtleView;
	private TurtleManager tManager;
	private FlowPane activeListDisplay;
	private List<Turtle> activeTurtles;
	
	public TurtleVisualView(View viewIn, Map map, double gbIndex){
		view = viewIn;
		tView = new TurtleView(view, map, gbIndex);
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		turtleView = new VBox();
		Label title = new Label("Toggle Visibility of Active Turtle");
		turtleView.getChildren().add(title);
		turtleView.setStyle(resource.getString("Border"));
		
		
	}  
	
	private List<Turtle> createActiveDisplay() {
		activeListDisplay = new FlowPane();
		activeListDisplay.setPrefWrapLength(turtleView.getWidth());
		if (tManager != null) {
			activeTurtles = tManager.getActiveTurtles();
			for(Turtle turtle: activeTurtles) {
				Label currentTurtle = new Label(turtle.toString());
				turtleView.getChildren().add(currentTurtle);
				toggleTurtle(currentTurtle, turtle);
			}
			return activeTurtles;
		}
		return null;
	}
	
	private void toggleTurtle(Label display, Turtle turtle) {
		display.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent t) {
	            if (tView.getParent().getChildrenUnmodifiable().contains(turtle.getImage())) {
	            	tView.removeTurtle(turtle.getImage());
	            }
	            else {
	            	tView.placeTurtle(turtle.getImage());
	            }
	        }
	    });
	}
	
	@Override
	public Parent getParent() {
		return turtleView;
	}
	
	public void updateActive() {
		turtleView.getChildren().remove(activeTurtles);
		createActiveDisplay();
	}

	public void setTurtleManager(TurtleManager tIn) {
		tManager = tIn;
		activeTurtles = createActiveDisplay();
	}
}
