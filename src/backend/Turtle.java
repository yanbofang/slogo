package backend;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {
	
	private final String myTurtlePicture = "turtle.png";
	
	private ImageView myImage;
	

	
	public Turtle(double width, double height) {
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));
		myImage.setImage(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
	}
	
	
	public Node getImage() {
		return myImage;
	}
	
	public void setX(double x) {
		myImage.setX(x);
	}
	
	public void setY(double y) {
		myImage.setY(y);
	}
	
	public Coordinate getLocation() {
		return new Coordinate(myImage.getX(), myImage.getY());
	}
	
}
