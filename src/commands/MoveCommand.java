package commands;

import java.lang.reflect.Method;

import coordinate.Coordinate;
import javafx.scene.image.ImageView;

public class MoveCommand extends AbstractCommand {

	private ImageView myTurtle;
	private int myQuadrant;
	
	public MoveCommand(String instruction) {
		super(instruction);
		myTurtle = new ImageView();
		myTurtle.setX(0);
		myTurtle.setY(0);
		myTurtle.setRotate(315);
	}

	@Override
	public Double getValue() {
		myValue = calculateValue();
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}
	
	public Double calculateValue() {
		Double value = 0.0;
		try {
			Method method = getClass().getDeclaredMethod(myInstruction, null);
			value = (Double) method.invoke(this, null);
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	private Double forward() {
		Double movement = (Double) myArguments.get(0);
		Coordinate coord = getNewCoord(movement);
		updateCoords(coord, myQuadrant);
		moveTurtle(coord);
		return movement;
	}
	
	private Double left() {
		Double degrees = (Double) myArguments.get(0);
		myTurtle.setRotate(myTurtle.getRotate() - degrees);
		return degrees;
	}
	
	private Double right() {
		Double degrees = (Double) myArguments.get(0);
		myTurtle.setRotate(myTurtle.getRotate() + degrees);
		return degrees;
	}
	
	private Double setheading() {
		Double degrees = (Double) myArguments.get(0);
		Double difference = myTurtle.getRotate() - degrees;
		myTurtle.setRotate(degrees);
		return difference;
	}
	
	private Double backward() {
		Double movement = (Double) myArguments.get(0);
		Coordinate coord = getNewCoord(movement);
		coord.setX(coord.getX()*-1);
		coord.setY(coord.getY()*-1);
		updateCoords(coord, myQuadrant);
		moveTurtle(coord);
		return movement;
	}
	
	private Double setposition() {
		Double updatedX = (Double) myArguments.get(0);
		Double updatedY = (Double) myArguments.get(1);
		Coordinate coord = new Coordinate(updatedX, updatedY);
		Double distance = calcDistance(coord, new Coordinate(myTurtle.getX(), myTurtle.getY()));
		moveTurtle(coord);
		return distance;
		
	}
	
	private Double settowards() {
		/**
		Coordinate newDirection = new Coordinate((Double) myArguments.get(0), 
				(Double) myArguments.get(1));
		Coordinate oldDirection = getNewCoord(new Double(10));
		Coordinate currentPosition = new Coordinate(myTurtle.getX(), myTurtle.getY());
		Double turtleToNew = calcDistance(newDirection, currentPosition);
		Double turtleToOld = calcDistance(oldDirection, currentPosition);
		Double newToOld = calcDistance(newDirection, oldDirection);
		Double toRotate = calcRotation(turtleToNew, turtleToOld, newToOld);**/
		
		Coordinate newDirection = new Coordinate((Double) myArguments.get(0), 
				(Double) myArguments.get(1));
		Coordinate neutralDirection = new Coordinate(myTurtle.getX(), myTurtle.getY()+10);
		Coordinate currentPosition = new Coordinate(myTurtle.getX(), myTurtle.getY());
		Double turtleToNew = calcDistance(newDirection, currentPosition);
		Double turtleToNeutral = calcDistance(neutralDirection, currentPosition);
		Double newToNeutral = calcDistance(newDirection, neutralDirection);
		Double toRotate = calcRotation(turtleToNew, turtleToNeutral, newToNeutral);
		Double difference = 
		
		return toRotate;
	}
	
	private Coordinate getNewCoord(Double movement) {
		double rotate = myTurtle.getRotate();
		myQuadrant = 1;
		while (rotate - 90 >= 0) {
			rotate -= 90;
			myQuadrant += 1;
		}
		if (myQuadrant == 1 || myQuadrant == 3) {
			rotate = 90 - rotate;
		}
		Double newY = (movement * Math.sin(Math.toRadians(rotate))) + myTurtle.getY();
		Double newX = (movement * Math.cos(Math.toRadians(rotate))) + myTurtle.getX();
		Coordinate coord = new Coordinate(newX, newY);
		return coord;
	}
	
	private void updateCoords(Coordinate coord, int quadrant) {
		switch (quadrant) {
		case 1: break;
		case 2: coord.setY(coord.getY()*-1);
				break;
		case 3: coord.setX(coord.getX()*-1); 
				coord.setY(coord.getY()*-1);
				break;
		case 4: coord.setX(coord.getX()*-1); 
				break;
		}
	}
	
	private void moveTurtle(Coordinate coord) {
		myTurtle.setX(coord.getX());
		myTurtle.setY(coord.getY());
	}
	
	private Double calcDistance(Coordinate firstCoord, Coordinate secondCoord) {
		Double xDiff = firstCoord.getX() - secondCoord.getX();
		Double yDiff = firstCoord.getY() - secondCoord.getY();
		Double distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
		return distance;
	}
	
	private Double calcRotation(Double side1, Double side2, Double side3) {
		Double side1Square = side1*side1;
		Double side2Square = side2*side2;
		Double side3Square = side3*side3;
		Double rotationAngle = Math.acos((side1Square + side2Square - side3Square) / (2*side1*side2));
		return rotationAngle;
	}

}
