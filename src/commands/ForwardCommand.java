package commands;


import coordinate.Coordinate;

public class ForwardCommand extends MoveCommand {

	public ForwardCommand(String instruction) {
		super(instruction);
	}
	
	public Double calculateValue() {
		return forward();
	}

	private Double forward() {
		Double movement = (Double) myArguments.get(0);
		Coordinate coord = getNewCoord(movement);
		updateCoords(coord, myQuadrant);
		moveTurtle(coord);
		return movement;
	}
}
