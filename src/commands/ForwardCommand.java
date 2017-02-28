package commands;


import backend.VariableManager;
import coordinate.Coordinate;

public class ForwardCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;

	public ForwardCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
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
