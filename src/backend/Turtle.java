package backend;

import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends Observable{
	
	private final String myTurtlePicture = "images/turtle.png";
	
	private ImageView myImage;
	

	
	public Turtle(double width, double height) {
		//myImage = new ImageView();
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));
		myImage = new ImageView(turtleView);
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
