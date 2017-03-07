package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Double getValue(List<Object> args, VariableManager vars) {
		Double returnValue = 0.0;
		
		Map<String, Variable> globalVariables = vars.getVariableMap();
		
		VariableManager localVariables = new VariableManager();
		
		localVariables.addAll(globalVariables);
		
		// Create the variables
		for (int i = 0; i < this.getNumOfExpressions(); i++) {
			localVariables.addVariable(
					new Variable(myMethod.getListOfVariables().get(i), (Double) args.get(i)));
		}

		List<Command> commands = myMethod.getListOfCommands();
		System.out.println(commands);
		for (Command c : commands) {
			c.resetCommand();
			while (!c.isFinished()) {
				returnValue = c.executeCommand(myTurtle, localVariables);
			}
		}
		return returnValue;
	}
	
}
