package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;
import turtles.TurtleManagerCommandAPI;

public abstract class LoopCommand extends AbstractCommand {

	protected Command myListCommand;
	protected VariableManager localVariables;

	public LoopCommand(String instruction, VariableManager variables, UserMethodManager methods, int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
		runNested = false;
		runAllTurtles = true;
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		myListCommand = (Command) args.get(1);
		return calculate(args, vars);
	}

	protected abstract Double calculate(List<Object> args, VariableManager vars);

	protected Double runCommands(Double start, Double end, Double increment, Variable var, VariableManager vars,
			Double k) {
		Double returnValue = 0.0;
		for (int i = start.intValue(); i < end.intValue() + 1; i += increment.intValue()) {
			if (var != null) {
				vars.addVariable(new Variable(var.getVariableName(), (double) i));
			}
			myListCommand.resetCommand();
			returnValue = myListCommand.executeCommand(myTurtleManager, vars, k);
		}
		return returnValue;
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		localVariables = new VariableManager();
		localVariables.addAll(vars.getVariableMap());
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		myConvertedArguments.add(myArguments.get(0).getAllArguments());
		myConvertedArguments.add(myArguments.get(1));
		this.changeToFinished();
		return this.getValue(myConvertedArguments, localVariables);
	}
}
