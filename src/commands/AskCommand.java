package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class AskCommand extends LoopCommand {

	private static final int NUM_OF_EXPRESSIONS = 2;

	public AskCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}
	
	@Override
	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = new ArrayList<Object>();
		convArgs.add(convertArguments(myArguments.get(0).getAllArguments(), vars, true));
		convArgs.add(myArguments.get(1));
		return convArgs;
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		List<Double> lst = (List<Double>) args.get(0);
		Double returnValue = 0.0;
		for (Double d : lst) {
			returnValue = runCommands(1.0, 1.0, 1.0, null, vars, d);
		}
		return returnValue;
	}

}
