package commands;

import java.util.List;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class ForCommand extends LoopCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8273555395033115049L;

	public ForCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods);
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		Variable var = new Variable(((Command) args.get(0)).getInstruction(),
				((Command) args.get(1)).executeCommand(getTurtleManager(), vars, getTurtle().getID()));
		vars.addVariable(var);
		return runCommands(((Command) args.get(1)).executeCommand(getTurtleManager(), vars, getTurtle().getID()),
				((Command) args.get(2)).executeCommand(getTurtleManager(), vars, getTurtle().getID()),
				((Command) args.get(3)).executeCommand(getTurtleManager(), vars, getTurtle().getID()), var, vars,
				getTurtle().getID());
	}

}
