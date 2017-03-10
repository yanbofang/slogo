package commands;


import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class SetPositionCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private Coordinate myCoord;

	public SetPositionCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	@Override
	public Double calculateValue(List<Object> args) {
		Double updatedX = (Double) args.get(0);
		Double updatedY = (Double) args.get(1);
		myCoord = new Coordinate(updatedX, updatedY*-1);
		updateCoords(myCoord, myQuadrant);
		Double distance = calcDistance(myCoord, getTurtle().getLocation(true));
		getTurtle().setLocation(myCoord, false);
		return distance;
	}

}
