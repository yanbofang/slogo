package commands;

import coordinate.Coordinate;
import backend.UserMethodManager;
import backend.VariableManager;

public class HomeCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 0;
	private Coordinate myCoord;
	
	public HomeCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double calculateValue() {
		myCoord = new Coordinate(0.0,0.0);
		myValue = calcDistance(myCoord, myTurtle.getFutureLocation());
		myTurtle.setFutureLocation(myCoord);
		return myValue;
	}
	
	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setLocation(myCoord, false);
		return myValue;
	}

	
}
