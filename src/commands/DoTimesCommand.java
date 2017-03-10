package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.TurtleManagerCommandAPI;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class DoTimesCommand extends LoopCommand {

	private static final Integer NUM_OF_EXPRESSIONS = 2;

	public DoTimesCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
		setRunAllTurtles(true);
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		List<Command> lst = (List<Command>) args.get(0);
		Variable var = new Variable(((Command) lst.get(0)).getInstruction(), 1.0);
		vars.addVariable(var);
		return runCommands(1.0, ((Command) lst.get(1)).executeCommand(getTurtleManager(), vars, getTurtle().getID()), 1.0,
				var, vars, getTurtle().getID());
	}

}
