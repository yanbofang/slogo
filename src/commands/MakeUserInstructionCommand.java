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
	public Double getValue(List<Object> args, VariableManager vars) {
		return getValue();
	}

	@Override
	public void performBeforeExecution() {
		UserMethod method = makeMethod(getArguments().get(2).getAllArguments());
		setValue(method.getMethodName().isEmpty() ? 0.0 : 1.0);
		return;
	}

	@Override
	public Double executeCommand(TurtleManagerCommandAPI turtles, VariableManager vars, Double k) {
		this.changeToFinished();
		return getValue();
	}

	@Override
	public void add(Command... each) {
		super.add(each);
		if (getArguments().size() == 2) {
			makeMethod(null);
		}
	}

	private UserMethod makeMethod(List<Command> function) {
		int numOfVariables = 0;
		List<String> variablesNameList = new ArrayList<String>();
		for (Command c : getArguments().get(1).getAllArguments()) {
			numOfVariables++;
			variablesNameList.add(c.getInstruction());
		}
		String name = getArguments().get(0).getInstruction();
		// Create the UserMethod as a command, instruction is the name of the
		// method

		UserMethod method = new UserMethod(name, function, variablesNameList);
		getUserMethods().add(name, method);
		return method;
	}

}
