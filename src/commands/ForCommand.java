package commands;

import java.util.ArrayList;
import java.util.List;

import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class ForCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public ForCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	public Double getValue(List<Object> args) {
		myCommands = (ArrayList<Command>) args.get(1);
		List<Object> lst = (List<Object>) args.get(0);
		lst = checkList(lst);
		Variable var = new Variable((String) lst.get(0), (Double) lst.get(1));
		myVariables.addVariable(var);
		return runCommands((Double) lst.get(1), (Double) lst.get(2), (Double) lst.get(3), var);
	}

}
