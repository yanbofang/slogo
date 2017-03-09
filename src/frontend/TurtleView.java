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

public class TurtleView implements TurtleViewerAPI {

	private View view;
	private ResourceBundle resource;
	private Pane viewer;
	private ArrayList<Line> lines;
	private Map<Double, String> colorMap;
	private MoveAnimation animation;
	private double colorIndex;

	public TurtleView(View viewIn, Map<Double, String> mapIn, double bgIndex) {
		view = viewIn;
		colorMap = mapIn;
		colorIndex = bgIndex;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		viewer = new Pane();
		viewer.setBackground(new Background(new BackgroundFill(Color.valueOf(colorMap.get(colorIndex)), CornerRadii.EMPTY, Insets.EMPTY)));
		lines = new ArrayList<Line>();
	}

	public void changePosition(Coordinate oldC, Coordinate newC, Pen pen, Turtle turtle) {
		//animation = new MoveAnimation(view, turtle.getImage(), oldC, newC);
		if (pen.showPen()) {
			Line newLine = new Line(oldC.getX(),oldC.getY(),newC.getX(),newC.getY());
			newLine.setStroke(Color.valueOf(colorMap.get(pen.getColor())));
			newLine.setStrokeWidth(pen.getSize());
			lines.add(newLine);
			viewer.getChildren().add(newLine);
		}
	}

	public void clear() {
		for (Line line : lines) {
			viewer.getChildren().remove(line);
		}
	}

	@Override
	public Parent getParent() {
		return viewer;
	}

	@Override
	public Coordinate getBounds() {
		double XCord = viewer.getWidth();
		double YCord = viewer.getHeight();
		Coordinate cord = new Coordinate(XCord, YCord);
		return cord;
	}

	@Override
	public void setBackgroundColor(String a) {
		for(double k : colorMap.keySet()){
			if(a.equals(colorMap.get(k))){
				colorIndex = k;
			}
		}
		Color tempColor = Color.valueOf(a);
		viewer.setBackground(new Background(new BackgroundFill(tempColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setBackgroundColor(double d){
		colorIndex = d;
		Color tempColor = Color.valueOf(colorMap.get(d));
		viewer.setBackground(new Background(new BackgroundFill(tempColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	@Override
	public void placeTurtle(Node a) {
		viewer.getChildren().add(a);
	}

	public void removeTurtle(Node a) {
		viewer.getChildren().remove(a);
	}
	
	public boolean containsTurtle(Node n) {
		return viewer.getChildren().contains(n);
	}
	
	public void updateColor(double indexIn, String colorIn){
		colorMap.put(indexIn, colorIn);
	}
	
	public double getBackgroundColor(){
		return colorIndex;
	}

}