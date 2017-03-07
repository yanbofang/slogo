package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public abstract class ConditionalCommand extends AbstractCommand {

	public ConditionalCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
	}

	@Override
	public abstract Double getValue(List<Object> args, VariableManager vars);

	protected Double execute(Double expr, List<Command> commandList, boolean ifElse, VariableManager vars) {
		Double returnValue = 0.0;
		if (ifElse == false && expr == 0.0) {
			return 0.0;
		}
		for (Command c : commandList) {
			c.resetCommand();
			while (!c.isFinished()) {
				returnValue = c.executeCommand(myTurtleManager, vars, myTurtle.getID());
			}
		}
		return returnValue;
	}

}
