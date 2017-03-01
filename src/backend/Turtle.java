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
	//private double myFutureRotate;
	//private Coordinate myFutureLocation;
	private boolean myPen;
	private boolean showTurtle;
	//private boolean myFuturePen;
	//private boolean myFutureShow;
	private boolean myClear;
	

	
	public Turtle(double width, double height, double widthBounds, double heightBounds) {
		//myImage = new ImageView();
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));

		myHeightBounds = heightBounds + height;
		myWidthBounds = widthBounds + width;
		myImage = new ImageView(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myImage.setTranslateX(myWidthBounds/2.0);
		myImage.setTranslateY(myHeightBounds/2.0);
		myImage.setRotate(0);
		//myFutureRotate = 0.0;
		//myFutureLocation = new Coordinate(0.0, 0.0);
		myPen = true;
		//myFuturePen = true;
		showTurtle = true;
		//myFutureShow = showTurtle;
		myClear = false;
	}
	
	public Node getImage() {
		return myImage;
	}
	

	public void setImage(Image a) {
		myImage.setImage(a);
	}
	
	private Double setX(double x) {
		if (x > myWidthBounds) {
			x -= myWidthBounds;
		} else if (x < 0) {
			x += myWidthBounds;
		}
		myImage.setTranslateX(x);
		return x;
	}
	
	private Double setY(double y) {
		if (y > myHeightBounds) {
			y -= myHeightBounds;
		} else if (y < 0) {
			y += myHeightBounds;
		}
		myImage.setTranslateY(y);
		return y;
	}
	
	public void setLocation(Coordinate coord, boolean ajusted) {
		if (!ajusted) {
			coord = setUnadjustedLocation(coord);
		}
		ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
		temp.add(new Coordinate(myImage.getTranslateX(), myImage.getTranslateY()));
		setX(coord.getX());
		setY(coord.getY());
		temp.add(coord);
		setChanged();
		notifyObservers(temp);
	}
	/*
	
	public void setFutureRotate(double rotate) {
		while (rotate >= 360) {
			rotate -= 360;
		}
		myFutureRotate = rotate;
	}
	
	public Double getFutureRotate() {
		return myFutureRotate;
	}*/
	
	private Coordinate setUnadjustedLocation(Coordinate coord) {
		coord.setX(coord.getX()+myWidthBounds/2.0);
		coord.setY(coord.getY()+myHeightBounds/2.0);
		return coord;
	}
	/*
	public Coordinate getFutureLocation(boolean adjust) {
		if (adjust) {
			return setUnadjustedLocation(new Coordinate(new Double(myFutureLocation.getX())
			, new Double(myFutureLocation.getY())));
		}
		return myFutureLocation;
	}
	
	public void setFutureLocation(Coordinate newFuture) {
		Double newX = newFuture.getX();
		Double newY = newFuture.getY(); 
		
		if (newX > myWidthBounds/2.0 - myImage.getFitWidth()) {
			newX -= myWidthBounds;
		} else if (newX < -myWidthBounds/2.0 + myImage.getFitWidth()) {
			newX += myWidthBounds;
		}
		if (newY > myHeightBounds/2.0 - myImage.getFitHeight()) {
			newY -= myHeightBounds;
		} else if (newY < -myHeightBounds/2.0 + myImage.getFitHeight()) {
			newY += myHeightBounds;
		}
		myFutureLocation.setX(newX);
		myFutureLocation.setY(newY);
	}*/
	
	public Coordinate getLocation(boolean unadjust) {
		Coordinate current = new Coordinate(myImage.getTranslateX(),
				myImage.getTranslateY());
		if (unadjust) {
			current = unadjust(current);
		}
		return current;
	}
	
	private Coordinate unadjust(Coordinate coord) {
		coord.setX(coord.getX() - myWidthBounds/2.0);
		coord.setY(coord.getY() - myHeightBounds/2.0);
		return coord;
	}
	
	public void setRotate(double rotate) {
		while (rotate >= 360 ) {
			rotate -= 360;
		}
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
		this.penChange();
	}
	/*
	public void setFuturePen(boolean b) {
		myFuturePen = b;
	}
	
	public boolean getFuturePen() {
		return myFuturePen;
	}
	*/
	public void setShow(boolean b) {
		showTurtle = b;
	}
	/*
	public void setFutureShow(boolean b) {
		myFutureShow = b;
	}
	
	public boolean getFutureShow() {
		return myFutureShow;
	}
	*/
	public boolean getClear() {
		return myClear;
	}
	
	public void setClear(boolean b) {
		myClear = b;
		if(b){
			penChange();
		}
	}
	
	private void penChange(){
		this.setChanged();
		boolean temp[] = {myClear, myPen};
		notifyObservers(temp);
	}
}
