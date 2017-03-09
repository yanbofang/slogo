package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.VariableManager;

public abstract class ConditionalCommand extends AbstractCommand {

	public ConditionalCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
	}

	@Override
	public abstract Double getValue(List<Object> args, VariableManager vars);

	protected Double execute(Double expr, Command commandList, boolean ifElse, VariableManager vars) {
		Double returnValue = 0.0;
		if (ifElse == false && expr == 0.0) {
			return 0.0;
		}
		commandList.resetCommand();
		while (!commandList.isFinished()) {
			returnValue = commandList.executeCommand(myTurtleManager, vars, myTurtle.getID());
		}
		return returnValue;
	}
	
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		myTurtleManager = turtles;
		VariableManager localVariables = vars;
		myTurtle = turtles.getTurtle(k);
		myConvertedArguments = new ArrayList<Object>();
		for (Command c: myArguments) {
			if (myArguments.indexOf(c) == 0) {
				myConvertedArguments.addAll(convertArguments(c, localVariables, true));
			} else {
				myConvertedArguments.add(c);
			}
		}
		this.changeToFinished();
		return this.getValue(myConvertedArguments, localVariables);
	}

}
