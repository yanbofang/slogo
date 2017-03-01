package commands;

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
	public Double getValue(List<Object> args) {
		if (args.get(0) instanceof Collection) {
			throw new ParserException("User method already created!");
		}
		String name = (String) args.get(0);
		System.out.println("executeCommand in makeuserinstruction" + (List<Command>) args.get(2));
		UserMethod method = new UserMethod(name, (List<Command>) args.get(2));
		myUserMethods.add(name, method);
		return method.getMethodName().isEmpty() ? 0.0 : 1.0;
	}

}
