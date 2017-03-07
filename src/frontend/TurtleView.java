package frontend;

import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import animation.MoveAnimation;
import coordinate.Coordinate;
import frontend.API.TurtleViewerAPI;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import turtles.Pen;
import turtles.Turtle;

public class TurtleView implements TurtleViewerAPI{

	private View view;
	private ResourceBundle resource;
	private Pane viewer;
	private ArrayList<Line> lines;
	private Color penColor;
	private Map<Double,String> colorMap;
	private MoveAnimation animation;
	
	public TurtleView(View viewIn, Map mapIn, double gbIndex) {
		view = viewIn;
		colorMap = mapIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		viewer = new Pane();
		viewer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		lines = new ArrayList<Line>();
	}
	
	public void changePosition(Coordinate oldC, Coordinate newC, Pen pen, Turtle turtle) {
		if (pen.showPen()) {
			
			animation = new MoveAnimation(view, turtle.getImage(), oldC, newC);
			Line newLine = new Line(oldC.getX(),oldC.getY(),newC.getX(),newC.getY());
			newLine.setStroke(Color.valueOf(colorMap.get(pen.getColor())));
			newLine.setStrokeWidth(pen.getSize());
			lines.add(newLine);
			viewer.getChildren().add(newLine);
		}	
	}

	public void clear() {
		for (Line line: lines) {
			viewer.getChildren().remove(line);
		}
	}
	
	@Override
	public Parent getParent() {
		return viewer;
	}

	@Override
	public Coordinate getBounds() {
		double XCord = viewer.getPrefWidth();
		System.out.println(XCord);
		double YCord = viewer.getPrefHeight();
		Coordinate cord = new Coordinate(XCord, YCord);
		return cord;
	}

	@Override
	public void setBackgroundColor(String a) {
		Color tempColor = Color.valueOf(a);
		viewer.setBackground(new Background(new BackgroundFill(tempColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	@Override
	public void setPenColor(String a) {
		penColor = Color.valueOf(a);
	}

	@Override
	public void placeTurtle(Node a) {
		viewer.getChildren().add(a);	
	}
	
	public void removeTurtle(Node a) {
		viewer.getChildren().remove(a);
	}


}
