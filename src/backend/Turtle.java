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
	private boolean myPen;
	private boolean showTurtle;
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
		myPen = true;
		showTurtle = true;
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
	
	private Coordinate setUnadjustedLocation(Coordinate coord) {
		coord.setX(coord.getX()+myWidthBounds/2.0);
		coord.setY(coord.getY()+myHeightBounds/2.0);
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
	
	public void setShow(boolean b) {
		showTurtle = b;
	}
	
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
