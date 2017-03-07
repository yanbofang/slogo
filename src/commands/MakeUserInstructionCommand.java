package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public Double getValue(List<Object> args, VariableManager vars) {
		if (args.get(0) instanceof MakeUserInstructionCommand) {
			throw new ParserException("User method already created!");
		}
		// make variables if haven't been made
		int numOfVariables = 0;
		List<String> variablesNameList = new ArrayList<String>();
		for (String variableName: (List<String>) args.get(1)) {
			numOfVariables++;
			variablesNameList.add(variableName);
		}
		String name = (String) args.get(0);
		//Create the UserMethod as a command, instruction is the name of the method

		UserMethod method = new UserMethod(name, (List<Command>) args.get(2), variablesNameList);
		UserMethodCommand userCommand = new UserMethodCommand(name, myVariables, myUserMethods, method);
		myUserMethods.add(name, method, userCommand);
		return method.getMethodName().isEmpty() ? 0.0 : 1.0;
	}

}
