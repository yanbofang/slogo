package commands;


import coordinate.Coordinate;

public abstract class MoveCommand extends AbstractCommand {

	protected int myQuadrant;
	
	public MoveCommand(String instruction) {
		super(instruction);
		myTurtle.setX(5);
		myTurtle.setY(0);
		myTurtle.setRotate(90);
	}

	@Override
	public Double getValue() {
		myValue = 0.0;
		try {
			myValue = calculateValue();
		} catch (Exception e) {
			return null;
		}
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}
	
	public abstract Double calculateValue();
	

	protected Coordinate getNewCoord(Double movement) {
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
	
	protected void updateCoords(Coordinate coord, int quadrant) {
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
	
	protected void moveTurtle(Coordinate coord) {
		myTurtle.setX(coord.getX());
		myTurtle.setY(coord.getY());
	}
	
	protected Double calcDistance(Coordinate firstCoord, Coordinate secondCoord) {
		Double xDiff = firstCoord.getX() - secondCoord.getX();
		Double yDiff = firstCoord.getY() - secondCoord.getY();
		Double distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
		return distance;
	}
	
	protected Double calcRotation(Double side1, Double side2, Double side3) {
		Double side1Square = side1*side1;
		Double side2Square = side2*side2;
		Double side3Square = side3*side3;
		Double rotationAngle = Math.acos((side1Square + side2Square - side3Square) / (2*side1*side2));
		return rotationAngle;
	}

}
