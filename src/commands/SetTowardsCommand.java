package commands;

import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class SetTowardsCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private Double myRotate;

	public SetTowardsCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		Coordinate newDirection = new Coordinate((Double) myArguments.get(0), 
				(Double) myArguments.get(1));
		Coordinate currentPosition = myTurtle.getFutureLocation(true);
		Coordinate neutralDirection = new Coordinate(currentPosition.getX(), currentPosition.getY()-10);
		Double turtleToNew = calcDistance(newDirection, currentPosition);
		Double turtleToNeutral = calcDistance(neutralDirection, currentPosition);
		Double newToNeutral = calcDistance(newDirection, neutralDirection);
		myRotate = Math.toDegrees(calcRotation(turtleToNew, turtleToNeutral, newToNeutral));
		if (newDirection.getX() < currentPosition.getX()) {
			myRotate = 360 - myRotate;
		}
		Double difference = Math.abs(myTurtle.getFutureRotate() - myRotate);
		myTurtle.setFutureRotate(myRotate);
		return difference;
	}
	
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setRotate(myRotate);
		return myValue;
	}
	
	private Double calcRotation(Double side1, Double side2, Double side3) {
		Double side1Square = side1*side1;
		Double side2Square = side2*side2;
		Double side3Square = side3*side3;
		Double rotationAngle = Math.acos((side1Square + side2Square - side3Square) / (2*side1*side2));
		return rotationAngle;
	}
	
}
