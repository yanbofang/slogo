package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IsShowingCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 0;
	public IsShowingCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		
	}
	
	@Override
	public Double getValue(List<Object> args) {
		if (myTurtle.showTurtle()) {
			myValue = 1.0;
		} else {
			myValue = 0.0;
		}
		return myValue;
	}


}
