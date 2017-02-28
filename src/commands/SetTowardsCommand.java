package commands;

import coordinate.Coordinate;

public class SetTowardsCommand extends MoveCommand {

	public SetTowardsCommand(String instruction) {
		super(instruction);
	}
	
	public Double calculateValue() {
		return setTowards();
	}
	
	private Double setTowards() {

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
		return difference;
	}

	
}
