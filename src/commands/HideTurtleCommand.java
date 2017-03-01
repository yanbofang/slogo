package commands;

import backend.UserMethodManager;
import backend.VariableManager;

public class HideTurtleCommand extends AbstractCommand {
	
	private static final Integer NUM_OF_EXPRESSION = 0;
	
	public HideTurtleCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSION);
	}

	@Override
	public Double getValue() {
		myValue = 0.0;
		myTurtle.setFutureShow(false);
		return myValue;
	}

	@Override
	public Double executeCommand() {
		this.changeToFinished();
		myTurtle.setShow(false);
		return myValue;
	}

}
