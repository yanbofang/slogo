package frontend;

import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import animation.MoveAnimation;
import coordinate.Coordinate;
import frontend.API.TurtleViewerAPI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import turtles.Pen;

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
		viewer.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		lines = new ArrayList<Line>();
	}
	
	public void changePosition(Coordinate oldC, Coordinate newC, Pen pen) {
		// need turtle
//		animation = new MoveAnimation(view, pen, oldC, newC);
		Line newLine = new Line(oldC.getX(),oldC.getY(),newC.getX(),newC.getY());
		newLine.setStroke(Color.valueOf(colorMap.get(pen.getColor())));
		newLine.setStrokeWidth(pen.getSize());
		lines.add(newLine);
		viewer.getChildren().add(newLine);
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
		double XCord = viewer.getWidth();
		double YCord = viewer.getHeight();
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
