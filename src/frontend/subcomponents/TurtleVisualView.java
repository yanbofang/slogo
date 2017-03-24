package frontend.subcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import frontend.View;
import frontend.API.SubcomponentAPI;
import frontend.API.TurtleVisualViewAPI;
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

/**
 * creates and controls window that allows the visibility of a turtle to be changed
 * @author Faith
 * Relies on View class
 */
public class TurtleVisualView implements TurtleVisualViewAPI {

	private View view;
	private ResourceBundle resource;
	private VBox turtleView;
	private TurtleManager tManager;
	private FlowPane activeListDisplay;
	private List<Turtle> activeTurtles;
	private List<Label> labels;
	
	// Constructor
	public TurtleVisualView(View viewIn, double gbIndex){
		view = viewIn;
		labels = new ArrayList<Label>();
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		turtleView = new VBox();
		Label title = new Label("Toggle Visibility of Active Turtle");
		turtleView.getChildren().add(title);
		turtleView.setStyle(resource.getString("Border"));	
	}  
	
	/**
	 * creates window that shows list of active turtles and allows their visibility to be toggled
	 * @return activeTurtles
	 * List of active turtles
	 */
	private List<Turtle> createActiveDisplay() {
		activeListDisplay = new FlowPane();
		activeListDisplay.setPrefWrapLength(turtleView.getWidth());
		if (tManager != null) {
			activeTurtles = tManager.getActiveTurtles();
			for(Turtle turtle: activeTurtles) {
				Label currentTurtle = new Label(Double.toString(turtle.getID()));
				turtleView.getChildren().add(currentTurtle);
				labels.add(currentTurtle);
				toggleTurtle(currentTurtle, turtle);
			}
			return activeTurtles;
		}
		return null;
	}
	
	/**
	 * 
	 * @param display
	 * Label of turtle number
	 * @param turtle
	 * Turtle whose visibility is to be changed
	 */
	private void toggleTurtle(Label display, Turtle turtle) {
		display.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent t) {
	            if (view.containsTurtle(turtle.getImage())) {
	            	view.removeTurtle(turtle.getImage());
	            }
	            else {
	            	view.addTurtle(turtle.getImage());
	            }
	        }
	    });
	}
	
	@Override
	public Parent getParent() {
		return turtleView;
	}
	
	@Override
	public void updateActive() {
		for(Label l : labels){
			turtleView.getChildren().remove(l);
		}
		createActiveDisplay();
	}

	@Override
	public void setTurtleManager(TurtleManager tIn) {
		tManager = tIn;
		activeTurtles = createActiveDisplay();
	}
}
