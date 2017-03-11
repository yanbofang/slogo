package commands;

import java.util.ArrayList;
import java.util.List;
import backend.UserMethodManager;
import backend.Variable;
import backend.VariableManager;

public class RepeatCommand extends LoopCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5560327249224036748L;
	private static final Integer NUM_OF_EXPRESSIONS = 2;
	private static final String REPEAT_VARIABLE_NAME = ":repcount";

	public RepeatCommand(String instruction, VariableManager variables, UserMethodManager methods) {
		super(instruction, variables, methods, NUM_OF_EXPRESSIONS);
	}

	@Override
	protected Double calculate(List<Object> args, VariableManager vars) {
		Variable var = new Variable(REPEAT_VARIABLE_NAME, 1.0);
		vars.addVariable(var);
		return runCommands(1.0, (Double) args.get(0), 1.0, var, vars, getTurtle().getID());
	}
	
	@Override
	protected ArrayList<Object> argumentsToConvert(VariableManager vars) {
		ArrayList<Object> convArgs = new ArrayList<Object>();
		getArguments().stream()
			.forEach(c -> {
				if (getArguments().indexOf(c)%2 == 0) {
					convArgs.add(convertArguments(c, vars, true));
				} else {
					convArgs.add(c);
				}
			});
		return convArgs;
	}

}
