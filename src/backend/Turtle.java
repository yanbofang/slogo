package backend;

import java.util.ArrayList;
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
	private boolean myPen;
	private boolean showTurtle;
	private boolean myFuturePen;
	private boolean myFutureShow;
	private boolean myClear;
	

	
	public Turtle(double width, double height, double widthBounds, double heightBounds) {
		//myImage = new ImageView();
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));

		myHeightBounds = heightBounds - height;
		myWidthBounds = widthBounds - width;
		myImage = new ImageView(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myImage.setTranslateX(myWidthBounds/2.0);
		myImage.setTranslateY(myHeightBounds/2.0);
		myFutureRotate = myImage.getRotate();
		myFutureLocation = new Coordinate(0.0, 0.0);
		myPen = true;
		myFuturePen = true;
		showTurtle = true;
		myFutureShow = showTurtle;
		myClear = false;
	}
	
	public Node getImage() {
		return myImage;
	}
	
	private void setX(double x) {
		/* KEEP IN CASE BUT DON'T THINK WE NEED
		if (x > myWidthBounds) {
			x -= myWidthBounds;
		} else if (x < 0) {
			x += myWidthBounds;
		}*/
		myImage.setTranslateX(x);
	}
	
	private void setY(double y) {
		/* KEEP IN CASE BUT DON'T THINK WE NEED
		if (y > myHeightBounds) {
			y -= myHeightBounds;
		} else if (y < 0) {
			y += myHeightBounds;
		}*/
		myImage.setTranslateY(y);
	}
	
	public void setLocation(Coordinate coord, boolean ajusted) {
		if (!ajusted) {
			coord = setUnadjustedLocation(coord);
		}
		ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
		temp.add(getLocation());
		setX(coord.getX());
		setY(coord.getY());
		temp.add(getLocation());
		setChanged();
		notifyObservers(temp);
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
	
	public Coordinate getFutureLocation(boolean adjust) {
		if (adjust) {
			return setUnadjustedLocation(new Coordinate(myFutureLocation.getX(), myFutureLocation.getY()));
		}
		return myFutureLocation;
	}
	
	public void setFutureLocation(Coordinate newFuture) {
		Double newX = newFuture.getX();
		Double newY = newFuture.getY(); 
		if (newX > myWidthBounds) {
			newX -= myWidthBounds;
		} else if (newX < 0) {
			newX += myWidthBounds;
		}
		if (newY > myHeightBounds) {
			newY -= myHeightBounds;
		} else if (newY < 0) {
			newY += myHeightBounds;
		}
		myFutureLocation.setX(newX);
		myFutureLocation.setY(newY);
	}
	
	public Coordinate getLocation() {
		return new Coordinate(myImage.getTranslateX() + myImage.getFitWidth()/2,
				myImage.getTranslateY() + myImage.getFitHeight()/2);
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
	
	public void setPen(boolean b) {
		myPen = b;
//		boolean temp = 
//		setChanged();
//		notifyObservers(temp);
	}
	
	public void setFuturePen(boolean b) {
		myFuturePen = b;
	}
	
	public boolean getFuturePen() {
		return myFuturePen;
	}
	
	public void setShow(boolean b) {
		showTurtle = b;
	}
	
	public void setFutureShow(boolean b) {
		myFutureShow = b;
	}
	
	public boolean getFutureShow() {
		return myFutureShow;
	}
	
	public boolean getClear() {
		return myClear;
	}
	
	public void setClear(boolean b) {
		myClear = b;
	}
}
