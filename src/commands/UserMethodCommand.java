package commands;

import java.util.List;

import backend.UserMethod;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class UserMethodCommand extends AbstractCommand {

	private UserMethod myMethod;
	
	public UserMethodCommand(String instruction, VariableManager variables, UserMethodManager methods,
			UserMethod method) {
		super(instruction, variables, methods, method.getListOfVariables().size());
		myMethod = method;
	}

	@Override
	public Double getValue(List<Object> args) {
		Double returnValue = 0.0;
		// Create the variables
		for (int i = 0; i < this.getNumOfExpressions(); i++) {
			myVariables.addVariable(
					new Variable(myMethod.getListOfVariables().get(i), (Double) args.get(i)));
		}

		List<Command> commands = myMethod.getListOfCommands();
		System.out.println(commands);
		for (Command c : commands) {
			c.resetCommand();
			while (!c.isFinished()) {
				returnValue = c.executeCommand(myTurtle);
			}
		}
		return returnValue;
	}
	
}
