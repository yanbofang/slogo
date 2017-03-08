package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public abstract class LoopCommand extends AbstractCommand {

	protected Command myListCommand;

	public LoopCommand(String instruction, VariableManager variables, UserMethodManager methods, int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
		runNested = false;
		runAllTurtles = true;
	}

	@Override
	public abstract Double getValue(List<Object> args, VariableManager vars);

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
}
