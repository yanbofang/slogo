package commands;

import backend.UserMethodManager;
import backend.VariableManager;
import coordinate.Coordinate;

public class BackwardCommand extends MoveCommand {
	

	private static final Integer NUM_OF_EXPRESSIONS = 1;
	private Coordinate myCoord;
	
	public BackwardCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	public Double calculateValue() {
		Double movement = (Double) myArguments.get(0);
		myCoord = getNewCoord(movement*-1);
		myTurtle.setFutureLocation(myCoord);
		return movement;
	}
	
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setLocation(myCoord, false);
		return myValue;
	}

}
