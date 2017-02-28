package commands;

import backend.VariableManager;
import coordinate.Coordinate;

public class BackwardCommand extends MoveCommand {
	

	protected static final Integer NUM_OF_EXPRESSIONS = 1;
	
	public BackwardCommand(String instruction, VariableManager manager) {
		super(instruction, manager, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		return backward();
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

}
