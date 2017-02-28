package commands;

import java.util.List;

import backend.UserMethod;
import backend.UserMethodManager;
import backend.VariableManager;

public class MakeUserInstructionCommand extends AbstractCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 3;

	public MakeUserInstructionCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double executeCommand() {
		// TODO Auto-generated method stub
		String name = (String) myArguments.get(0);
		System.out.println((List<Command>) myArguments.get(2));
		UserMethod method = new UserMethod(name, (List<Command>) myArguments.get(2));
		myUserMethods.add(name, method);
		this.changeToFinished();
		return method.getMethodName().isEmpty() ? 0.0 : 1.0;
	}

}
