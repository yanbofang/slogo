package commands;


import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public abstract class MoveCommand extends AbstractCommand {

	protected int myQuadrant;
	
//	
//	public MoveCommand(String instruction, VariableManager variables) {
//		super(instruction, variables, 0);
//	}
	
	
	public MoveCommand(String instruction, VariableManager variables, UserMethodManager methods, int numberOfExpressions) {
		super(instruction, variables, methods, numberOfExpressions);
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
	public abstract Double executeCommand();
	public abstract Double calculateValue();
	
	protected Coordinate getNewCoord(Double movement) {
		double rotate = myTurtle.getFutureRotate();
		myQuadrant = 1;
		while (rotate - 90 >= 0) {
			rotate -= 90;
			myQuadrant += 1;
		}
		if (myQuadrant == 1 || myQuadrant == 3) {
			rotate = 90 - rotate;
		}
		Coordinate currLocation = myTurtle.getFutureLocation();
		Coordinate movementCoord = new Coordinate(movement * Math.cos(Math.toRadians(rotate)),
				movement * Math.sin(Math.toRadians(rotate)));
		updateCoords(movementCoord, myQuadrant);
		Double newX = movementCoord.getX() + currLocation.getX();
		Double newY = movementCoord.getY() + currLocation.getY();
		
		Coordinate coord = new Coordinate(newX, newY);
		return coord;
	}
	
	protected void updateCoords(Coordinate coord, int quadrant) {
		switch (quadrant) {
		case 1: coord.setY(coord.getY()*-1);
				break;
		case 2: break;
		case 3: coord.setX(coord.getX()*-1); 
				break;
		case 4: coord.setX(coord.getX()*-1);
				coord.setY(coord.getY()*-1);
				break;
		}
	}
	/**
	protected void moveTurtle(Coordinate coord) {
		myTurtle.changeLocation(new Coordinate(coord));
		myTurtle.setX(coord.getX());
		myTurtle.setY(coord.getY());
	}**/
	
	protected Double calcDistance(Coordinate firstCoord, Coordinate secondCoord) {
		Double xDiff = firstCoord.getX() - secondCoord.getX();
		Double yDiff = firstCoord.getY() - secondCoord.getY();
		Double distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
		return distance;
	}

}
