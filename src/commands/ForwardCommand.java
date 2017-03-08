package commands;


import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class ForwardCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 1;
	private Coordinate myCoord;

	public ForwardCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	@Override
	public Double calculateValue(List<Object> args) {
		Double movement = (Double) args.get(0);
		System.out.println("IN FORWARD" + movement);
		myCoord = getNewCoord(movement);

		System.out.println("IN FORWARD" + myCoord.getX() + myCoord.getY());
		myTurtle.setLocation(myCoord, false);

		return movement;
	}

}
