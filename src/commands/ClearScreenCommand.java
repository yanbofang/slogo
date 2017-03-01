package commands;

import coordinate.Coordinate;
import backend.UserMethodManager;
import backend.VariableManager;

public class ClearScreenCommand extends MoveCommand {
	
	private static final Integer NUM_OF_EXPRESSIONS = 0;
	private Coordinate myCoord;

	public ClearScreenCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double calculateValue() {
		myCoord = toHome();
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setLocation(myCoord, false);
		myTurtle.setClear(true);
		return myValue;
	}


}
