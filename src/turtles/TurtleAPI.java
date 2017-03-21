package turtles;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface TurtleAPI {

	/**
	 * @return Turtle heading
	 */
	Double getRotate();

	/**
	 * @return Return Location of turtle with (0,0) at center of screen
	 */
	Coordinate getLocation();

	/**
	 * Set Image to index A (map from index to image held in front end)
	 * @param a
	 */
	void setImage(Image a);

	/**
	 * @return Turtle's pen object
	 */
	Pen getPen();

	/**
	 * Get turtle Image UI to be displayed in front end
	 * @return
	 */
	Node getImage();
}
