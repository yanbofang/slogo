package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
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
		setRunAllTurtles(true);
	}

	public UserMethodCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods);
		checkForMethod();
		setRunAllTurtles(true);
	}

	private boolean checkForMethod() {
		if (getUserMethods().getUserMethod(getInstruction()) != null) {
			myMethod = getUserMethods().getUserMethod(getInstruction());
			setNumOfExpressions(myMethod.getListOfVariables().size());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Double getValue(List<Object> args, VariableManager vars) {
		if (checkForMethod() == false) {
			throw new ParserException(String.format("NOT A VALID USER METHOD %s", getInstruction()));
		}

		Double returnValue = 0.0;
		VariableManager localVariables = new VariableManager();
		localVariables.addAll(vars.getVariableMap());

		for (int i = 0; i < this.getNumOfExpressions(); i++) {
			localVariables.addVariable(new Variable(myMethod.getListOfVariables().get(i), (Double) args.get(i)));
		}

		List<Command> commands = myMethod.getListOfCommands();
		System.out.println(commands + "IN USER METHOD COMMAND");
		for (Command c : commands) {
			c.resetCommand();
			while (!c.isFinished()) {
				returnValue = c.executeCommand(getTurtleManager(), localVariables, getTurtle().getID());
			}
		}
		return returnValue;
	}


}
