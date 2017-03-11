package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class IsShowingCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8981298499439195506L;
	private static final Integer NUM_OF_EXPRESSIONS = 0;
	public IsShowingCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		setRunAllTurtles(true);
	}
	
	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		if (getTurtle().showTurtle()) {
			setValue(1.0);
		} else {
			setValue(0.0);
		}
		return getValue();
	}


}
