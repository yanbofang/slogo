package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class YCoordinateCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	
	public YCoordinateCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue() {
		myValue = myTurtle.getFutureLocation().getY();
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		return myValue;
	}

}
