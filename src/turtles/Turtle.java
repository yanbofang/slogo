package turtles;

import java.util.ArrayList;
import java.util.Observable;

import coordinate.Coordinate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends Observable implements TurtleAPI{
	
	private final String myTurtlePicture = "images/turtle.png";
	
	private ImageView myImage;
	private double myHeightBounds;
	private double myWidthBounds;
	private Pen myPen;
	private boolean showTurtle;
	private boolean myClear;
	private Double myID;
	private Double myImageIndex;

	
	public Turtle(double width, double height, double widthBounds, double heightBounds, Double id) {
		Image turtleView = new Image(getClass().getClassLoader().getResourceAsStream(myTurtlePicture));
		myHeightBounds = heightBounds - height;
		myWidthBounds = widthBounds - width;
		myImage = new ImageView(turtleView);
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
		myImage.setTranslateX(myWidthBounds/2.0 );
		myImage.setTranslateY(myHeightBounds/2.0 );
		myImage.setRotate(0);
		myPen = new Pen();
		showTurtle = true;
		myClear = false;
		myID = id;
	}
	
	public Node getImage() {
		return myImage;
	}
	

	public void setImage(Image a) {
		myImage.setImage(a);
	}
	
	public void setImage(Double index) {
		myImageIndex = index;
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
		temp.add(new Coordinate(myImage.getTranslateX() + myImage.getFitWidth()/2, myImage.getTranslateY() + myImage.getFitHeight()/2));
		setX(coord.getX());
		setY(coord.getY());
		coord.setX(coord.getX() + myImage.getFitWidth()/2);
		coord.setY(coord.getY() + myImage.getFitHeight()/2);
		temp.add(coord);
		setChanged();
		notifyObservers(temp);

		}
	
	private Coordinate setUnadjustedLocation(Coordinate coord) {
		coord.setX(coord.getX() + myWidthBounds/2.0);
		coord.setY(coord.getY() + myHeightBounds/2.0);
		return coord;
	}
	
	
	public Coordinate getLocation(boolean unadjust) {
		Coordinate current = new Coordinate(myImage.getTranslateX(),
				myImage.getTranslateY());
		if (unadjust) {
			current = unadjust(current);
		}
		return current;
	}
	
	@Override
	public Coordinate getLocation() {
		return getLocation(true);
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
	
	public void setShow(boolean b) {
		showTurtle = b;
		this.setChanged();
		notifyObservers(b);
	}
	
	public Double getShape() {
		return myImageIndex;
	}

	
	public void setClear(boolean b) {
		myClear = b;
		if(b){
			penChange();
		}
	}
	
	private void penChange(){
		this.setChanged();
		notifyObservers();
		myClear = false;
	}

	@Override
	public Pen getPen() {
		return myPen;
	}
	
	public Double getID() {
		return myID;
	}
}
