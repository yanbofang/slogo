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
	public Double getValue(List<Object> args, VariableManager vars) {
		myCommands = (ArrayList<Command>) args.get(1);
		List<Double> lst = (List<Double>) args.get(0);
		Double returnValue = 0.0;
		for (Double d : lst) {
			returnValue = runCommands(1.0, 1.0, 1.0, null, vars, d);
		}
		return returnValue;
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(convertArguments((List<Object>) myArguments.get(0), vars, true));
		myConvertedArguments.add(convertArguments((List<Object>) myArguments.get(1), vars, false));
		this.changeToFinished();
		return this.getValue(myConvertedArguments, vars);
	}

}
