package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class ForCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public ForCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		runAllTurtles = true;
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		List<Command> lst = (List<Command>) args.get(0);
		List<Object> positions = convertArguments(lst.subList(1, lst.size()), vars, true);
		Variable var = new Variable(lst.get(0).getInstruction(), (Double) positions.get(0));
		vars.addVariable(var);
		return runCommands((Double) positions.get(0), (Double) positions.get(1), (Double) positions.get(2), var, vars,
				myTurtle.getID());
	}

}
