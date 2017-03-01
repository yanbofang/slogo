package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public abstract class ConditionalCommand extends AbstractCommand {

	public ConditionalCommand(String instruction, VariableManager variables, UserMethodManager methods,
			int numOfExpressions) {
		super(instruction, variables, methods, numOfExpressions);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract Double getValue();

	@Override
	public abstract Double executeCommand();

	protected Double execute(Double val, List<Command> commandList, boolean ifElse) {
		Double returnValue = val;
		if (ifElse == false && (Double) myArguments.get(0) == 0.0) {
			return 0.0;
		}
		for (Command c : commandList) {
			System.out.println("execute in conditional " + c.getInstruction() + "finished ?   " + c.isFinished() + "Value ");
			while (!c.isFinished()) {
				returnValue = c.executeCommand();
			}
		}
		return returnValue;
	}

}
