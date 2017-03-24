package frontend.subcomponents;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import controller.TurtleInfo;
import coordinate.Coordinate;
import frontend.View;
import frontend.API.StateViewAPI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import turtles.Pen;
import turtles.Turtle;
import turtles.TurtleManager;

/**
 * Subcomponent to handle display of information of all turtles
 * @author Gordon
 *
 */
public class StateView implements StateViewAPI {
	
	private HashMap<Double, Label> turtleStatus;
	
	private ScrollPane scrollPane;
	private VBox vBox;
	private View view;

	private ResourceBundle resource;
	private TurtleManager tm;

	public StateView(View viewIn) {
		view = viewIn;
		turtleStatus = new HashMap<Double, Label>();
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		setUpParent();
	}
	
	public Parent getParent() {
		return scrollPane;
	}
	
	public void setTurtleManager(TurtleManager tmIn){
		tm = tmIn;
		initialTurtles();
	}

	public void updateStatus(Double turtleID) {
		Label temp;
		if(!turtleStatus.containsKey(turtleID)){
			temp = makeLabel(tm.getTurtle(turtleID));
			turtleStatus.put(turtleID, temp);
		}
		else{
			temp = turtleStatus.get(turtleID);
		}
		temp.setText(turtleInfoString(tm.getTurtle(turtleID)));
	}
	
	/**
	 * Generate the initial list of turtle information
	 */
	private void initialTurtles(){
		TreeSet<Double> temp = new TreeSet<Double>(tm.getAllTurtleIDs());
		for(Double d : temp){
			Label l = makeLabel(tm.getTurtle(d));
			turtleStatus.put(d, l);
		}
	}
	
	/**
	 * Make a label of information for a specified turtle
	 * @param t
	 * Turtle to get information from
	 * @return
	 * Label with turtle information
	 */
	private Label makeLabel(Turtle t){
		Label ret = new Label();
		ret.setText(turtleInfoString(t));
		vBox.getChildren().add(ret);
		return ret;
	}
	
	/**
	 * Create a string that contains important information of a turtle
	 * @param t
	 * Turtle to get information from
	 * @return
	 * String with fully formatted information of the turtle
	 */
	private String turtleInfoString(Turtle t){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(resource.getString("TurtleID"), t.getID()));
		Coordinate pos = t.getLocation();
		sb.append(String.format(resource.getString("TurtlePos"), pos.getX(), pos.getY()));
		sb.append(String.format(resource.getString("TurtleHeading"), t.getRotate()));
		Pen pen = t.getPen();
		sb.append(String.format(resource.getString("TurtlePenDown"), pen.showPen()));
		sb.append(String.format(resource.getString("TurtlePenColor"), pen.getColor()));
		return sb.toString();
	}
	
	/**
	 * Set up the parent
	 */
	private void setUpParent(){
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefHeight(200);
		VBox layout = new VBox();
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10, 10, 0, 20));
		layout.setSpacing(20);
		scrollPane.setContent(layout);
		scrollPane.setFitToWidth(true);

		vBox = new VBox();
		vBox.setSpacing(10);
		Label title = new Label(resource.getString("StateTitle"));
		title.setTextAlignment(TextAlignment.CENTER);
		layout.getChildren().addAll(title, vBox);
	}

}
