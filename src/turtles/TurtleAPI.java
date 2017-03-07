package turtles;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface TurtleAPI {

	Double getRotate();

	Coordinate getLocation();

	void setImage(Image a);

	Pen getPen();

	Node getImage();
}
