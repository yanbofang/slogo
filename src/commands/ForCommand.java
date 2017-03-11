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
		List<Command> lst = (List<Command>) args.get(0);
		List<Object> positions = convertArguments(lst.subList(1, lst.size()), vars, true);
		Variable var = new Variable(lst.get(0).getInstruction(), (Double) positions.get(0));
		vars.addVariable(var);
		return runCommands((Double) positions.get(0), (Double) positions.get(1), (Double) positions.get(2), var, vars,
				getTurtle().getID());
	}

}
