package commands;

import coordinate.Coordinate;

public class BackwardCommand extends MoveCommand {
	
	public BackwardCommand(String instruction) {
		super(instruction);
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
