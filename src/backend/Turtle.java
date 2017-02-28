package backend;

import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends Observable{
	
	private final String myTurtlePicture = "images/turtle.png";
	
	private ImageView myImage;
	private double myHeightBounds;
	private double myWidthBounds;
	private double myFutureRotate;
	private Coordinate myFutureLocation;
	

	
	public Turtle(double width, double height, double heightBounds, double widthBounds) {
		//myImage = new ImageView();
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));
		myHeightBounds = heightBounds;
		myWidthBounds = widthBounds;
		myImage = new ImageView(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myImage.setX(myWidthBounds/2.0);
		myImage.setY(myHeightBounds/2.0);
		myFutureRotate = myImage.getRotate();
		myFutureLocation = new Coordinate(myImage.getX(), myImage.getY());
	}
	
	public Node getImage() {
		return myImage;
	}
	
	private void setX(double x) {
		if (x > myWidthBounds/2) {
			x -= myWidthBounds;
		} else if (x < 0) {
			x += myWidthBounds;
		}
		myImage.setX(x);
	}
	
	private void setY(double y) {
		if (y > myHeightBounds/2) {
			y -= myWidthBounds;
		} else if (y < 0) {
			y += myHeightBounds;
		}
		myImage.setY(y);
	}
	
	public void setLocation(Coordinate coord) {
		setX(coord.getX());
		setY(coord.getY());
	}
	
	public void setFutureRotate(double rotate) {
		myFutureRotate = rotate;
	}
	
	public Double getFutureRotate() {
		return myFutureRotate;
	}
	
	public void setUnadjustedLocation(Coordinate coord) {
		setX(coord.getX()+myWidthBounds/2.0);
		setY(coord.getY()+myHeightBounds/2.0);
	}
	
	public Coordinate getFutureLocation() {
		return myFutureLocation;
	}
	
	public void setFutureLocation(Coordinate newFuture) {
		myFutureLocation = newFuture;
	}
	
	public Coordinate getLocation() {
		return new Coordinate(myImage.getX(), myImage.getY());
	}
	
	public void setRotate(double rotate) {
		myImage.setRotate(rotate);
	}
	
	public Double getRotate() {

		return myImage.getRotate();
	}
	
	public Double getX() {
		return myImage.getX();
	}
	
	public Double getY() {
		return myImage.getY();
	}
}
