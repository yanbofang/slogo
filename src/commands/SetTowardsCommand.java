package commands;

import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class SetTowardsCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public SetTowardsCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);s
	}
	
	public Double calculateValue() {
		return setTowards();
	}
	
	public Double executeCommand() {

		Coordinate newDirection = new Coordinate((Double) myArguments.get(0), 
				(Double) myArguments.get(1));

		Coordinate neutralDirection = new Coordinate(myTurtle.getX(), myTurtle.getY()+10);
		Coordinate currentPosition = new Coordinate(myTurtle.getX(), myTurtle.getY());
		Double turtleToNew = calcDistance(newDirection, currentPosition);
		Double turtleToNeutral = calcDistance(neutralDirection, currentPosition);
		Double newToNeutral = calcDistance(newDirection, neutralDirection);
		Double toRotate = Math.toDegrees(calcRotation(turtleToNew, turtleToNeutral, newToNeutral));
		if (newDirection.getX() < myTurtle.getX()) {
			toRotate += 180;
		}
		Double difference = Math.abs(myTurtle.getRotate() - toRotate);
		myTurtle.setRotate(toRotate);
		this.changeToFinished();
		return difference;
	}
	
	private Double calcRotation(Double side1, Double side2, Double side3) {
		Double side1Square = side1*side1;
		Double side2Square = side2*side2;
		Double side3Square = side3*side3;
		Double rotationAngle = Math.acos((side1Square + side2Square - side3Square) / (2*side1*side2));
		return rotationAngle;
	}
	
}
