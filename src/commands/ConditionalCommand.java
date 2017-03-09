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
		runAllTurtles = true;
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

	@Override
	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = new ArrayList<Object>();
		for (Command c: myArguments) {
			if (myArguments.indexOf(c) == 0) {
				convArgs.addAll(convertArguments(c, vars, true));
			} else {
				myConvertedArguments.add(c);
			}
		}
		return convArgs;
	}

}
