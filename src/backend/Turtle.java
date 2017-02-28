package backend;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {
	
	private final String myTurtlePicture = "images/turtle.png";
	
	private ImageView myImage;
	private double myHeightBounds;
	private double myWidthBounds;
	private double myFutureRotate;
	private Coordinate myFutureLocation;
	private boolean myPen;
	private boolean showTurtle;
	

	
	public Turtle(double width, double height, double heightBounds, double widthBounds) {
		//myImage = new ImageView();
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));
		myHeightBounds = heightBounds;
		myWidthBounds = widthBounds;
		myImage = new ImageView(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myImage.setTranslateX(myWidthBounds/2.0);
		myImage.setTranslateY(myHeightBounds/2.0);
		myFutureRotate = myImage.getRotate();
		myFutureLocation = new Coordinate(myImage.getX(), myImage.getY());
		myPen = true;
		showTurtle = true;
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
		myImage.setTranslateX(x);
	}
	
	private void setY(double y) {
		if (y > myHeightBounds/2) {
			y -= myWidthBounds;
		} else if (y < 0) {
			y += myHeightBounds;
		}
		myImage.setTranslateY(y);
	}
	
	public void setLocation(Coordinate coord, boolean ajusted) {
		if (!ajusted) {
			coord = setUnadjustedLocation(coord);
		}
		setX(coord.getX());
		setY(coord.getY());
	}
	
	public void setFutureRotate(double rotate) {
		myFutureRotate = rotate;
	}
	
	public Double getFutureRotate() {
		return myFutureRotate;
	}
	
	private Coordinate setUnadjustedLocation(Coordinate coord) {
		coord.setX(coord.getX()+myWidthBounds/2.0);
		coord.setY(coord.getY()+myHeightBounds/2.0);
		return coord;
	}
	
	public Coordinate getFutureLocation() {
		return myFutureLocation;
	}
	
	public void setFutureLocation(Coordinate newFuture, boolean adjusted) {
		if (!adjusted) {
			newFuture = setUnadjustedLocation(newFuture);
		}
		myFutureLocation = newFuture;
	}
	
	public Coordinate getLocation() {
		return new Coordinate(myImage.getTranslateX(), myImage.getTranslateY());
	}
	
	public void setRotate(double rotate) {
		myImage.setRotate(rotate);
	}
	
	public Double getRotate() {
		return myImage.getRotate();
	}
	
	public boolean showTurtle() {
		return showTurtle;
	}
	
	public boolean showPen() {
		return myPen;
	}
	
	public Coordinate getHome() {
		return new Coordinate(myWidthBounds/2.0, myHeightBounds/2.0);
	}
	
}
