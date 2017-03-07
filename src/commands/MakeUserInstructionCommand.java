package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.ParserException;
import backend.UserMethod;
import backend.UserMethodManager;
import backend.VariableManager;

public class MakeUserInstructionCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 3;

	public MakeUserInstructionCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
<<<<<<< HEAD
	public Double getValue(List<Object> args, VariableManager vars) {
		if (args.get(0) instanceof MakeUserInstructionCommand) {
			throw new ParserException("User method already created!");
		}
		// make variables if haven't been made
=======
	public Double getValue(List<Object> args) {
		return myValue;
	}

	@Override
	public void performBeforeExecution() {
>>>>>>> ae34916c40d34c41e75bf348766f340649d34ff2
		int numOfVariables = 0;
		List<String> variablesNameList = new ArrayList<String>();
		for (String variableName: (List<String>) myArguments.get(1)) {
			numOfVariables++;
			variablesNameList.add(variableName);
		}
		String name = (String) myArguments.get(0);
		//Create the UserMethod as a command, instruction is the name of the method

		UserMethod method = new UserMethod(name, (List<Command>) myArguments.get(2), variablesNameList);
		myUserMethods.add(name, method);
		myValue = method.getMethodName().isEmpty() ? 0.0 : 1.0;
		this.changeToFinished();
		return;
	}
	
	@Override 
	public Double executeCommand(TurtleManagerCommandAPI turtles, Double k) {
		this.changeToFinished();
		return myValue;
	}

}
