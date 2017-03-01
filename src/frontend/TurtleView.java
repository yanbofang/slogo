package frontend;

import java.util.ArrayList;
import java.util.ResourceBundle;

import coordinate.Coordinate;
import frontend.API.TurtleViewerAPI;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TurtleView implements TurtleViewerAPI{

	private View view;
	private ResourceBundle resource;
	private Pane viewer;
	private ArrayList<Line> lines;
	private Color penColor;
	
	
	public TurtleView(View viewIn) {
		view = viewIn;
		resource = ResourceBundle.getBundle(view.RESOURCE_BUNDLE);
		viewer = new Pane();
		viewer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		lines = new ArrayList<Line>();
		penColor = Color.RED;
	}
	
	
	public void changePosition(Coordinate oldC, Coordinate newC) {
		Line newLine = new Line(oldC.getX(),oldC.getY(),newC.getX(),newC.getY());
		System.out.println(penColor.toString());
		newLine.setStroke(penColor);
		lines.add(newLine);
		viewer.getChildren().add(newLine);
		System.out.println("test");
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

	public void placeTurtle(Node node) {
		viewer.getChildren().add(node);
	}

	@Override
	public void setPenColor(String a) {
		penColor = Color.valueOf(a);
	}

}
