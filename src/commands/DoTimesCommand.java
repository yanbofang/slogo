package commands;

import java.util.ArrayList;
import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class DoTimesCommand extends LoopCommand{

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public DoTimesCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
		// TODO Auto-generated constructor stub
	}
	
	public Double getValue(List<Object> args) {
		myCommands = (ArrayList<Command>) args.get(2);
		return runCommands(1.0, (Double) args.get(1), 1.0);
	}

}
