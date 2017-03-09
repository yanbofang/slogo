package commands;

import java.util.List;

import backend.UserMethodManager;
import backend.VariableManager;

public class SetPaletteCommand extends AbstractCommand {

	private static final int NUM_OF_EXPRESSIONS = 4;
	public SetPaletteCommand(String instruction, VariableManager variables,
			UserMethodManager methods) {
		super(instruction, variables, methods);
	}

	@Override
	public Double getValue(List<Object> args, VariableManager localVariables) {
		myTurtleManager.addColor((Double) args.get(0), (Double) args.get(1), (Double) args.get(2),
				(Double) args.get(3));
		return (Double) args.get(0);
	}

}
